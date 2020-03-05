import java.util.ArrayList;
import java.util.List;

import nl.moj.model.Tester;

public class ResumeHunterTester implements Tester.Testable {

    private static final String[] NAMES = new String[] { 
        "Single Match",
        "And Match",
        "Or Match",
        "Or And Match",
        "And Or Match",
        "Complex Match",
        "No Match"
    };

    private static final String[] CRITERIA = new String[] { 
        "(Java,NOVICE)",
        "(Java,SKILLED)&(Database,EXPERT)",
        "(Ruby,NOVICE)|(Java,NOVICE)|(Database,NOVICE)",
        "((Java,EXPERT)|(Ruby,EXPERT))&(Database,EXPERT)",
        "((Java,NOVICE)&(Database,NOVICE))|(Ruby,GURU)",
        "(((Java,EXPERT)|(Java,GURU))&((Ruby,NOVICE)|(Ruby,SKILLED)|(Ruby,EXPERT)|(Ruby,GURU))&((Database,NOVICE)|(Database,SKILLED)|(Database,EXPERT)))",
        "(Cobol,GURU)"
    };

    private static final String[] RESUMES = new String[] { 
        "JrDeJunior|Java,NOVICE|Ruby,NOVICE|Database,NOVICE",
        "AnthonyVanDerBerg|Java,SKILLED|Ruby,NOVICE|Database,EXPERT", 
        "ErikDeClicker|Java,EXPERT|Ruby,NOVICE|Database,SKILLED",
        "JervaasVanTypum|Java,EXPERT|Ruby,SKILLED|Database,EXPERT", 
        "ScriptKiddie|Ruby,GURU",
        "G.Guru|Java,GURU|Ruby,EXPERT|Database,EXPERT"
    };
    
    private static final boolean[][] RESULTS=new boolean[][] {
        { true ,false,false,false,false,false },
        { false,true ,false,false,false,false },
        { true ,true ,true ,false,false,false },
        { false,false,false,true ,false,true  },
        { true ,false,false,false,true ,false },
        { false,false,true ,true ,false,true  },
        { false,false,false,false,false,false },
    };

    public int getTestCount() {
        return NAMES.length;
    }

    public String getTestName(int nr) {
        return NAMES[nr];
    }

    public String getTestDescription(int nr) {
        StringBuffer sb = new StringBuffer();
        sb.append("In your database are "+RESUMES.length+" resumes of skilled individuals.\n");
        sb.append("Your customer is looking for someone matching the following criteria:\n");
        sb.append(CRITERIA[nr]);
        sb.append("\nApply the resumes to the criteria and find the matching resumes.\n");
        sb.append("\nExpected results:\n");
        for (int t=0;t<RESUMES.length;t++) {
            StringBuffer l=new StringBuffer();
            ResumeImpl r=new ResumeImpl(RESUMES[t]);
            l.append(RESULTS[nr][t]?"matches : ":"no match: ");
            l.append(r.getName());
            while (l.length()<27) l.append(" ");
            for (String s:r.getSkills()) {
                l.append(" "+s+"="+r.getExperienceForSkill(s));
            }
            l.append("\n");
            sb.append(l.toString());
        }
        //
        return sb.toString();
    }

    public boolean performTest(int nr) throws Throwable {
        //
        // Assume the worst
        //
        boolean result = true;
        //
        // Create a new Instance for each test.
        //
        try {
            ResumeHunterImpl instance = new ResumeHunterImpl();
            SkillExpert x = parse(instance, CRITERIA[nr]);
            boolean[] matches=new boolean[RESUMES.length];
            for (int t=0;t<RESUMES.length;t++) {
                Resume r=new ResumeImpl(RESUMES[t]);
                matches[t]=instance.matches(x,r);
                if (matches[t]!=RESULTS[nr][t]) {
                    System.out.println("The resume of '"+r.getName()+"' should "+(RESULTS[nr][t]?"":"not")+" have matched criteria:");
                    System.out.println(CRITERIA[nr]);
                    System.out.println(r);
                    result=false;
                }
            }
        } catch (Exception ex) {
            System.out.println("An exception is thrown. More peanuts are needed.");
            ex.printStackTrace();
            result=false;
        }
        //
        return result;
    }

    private SkillExpert parse(SkillExpertFactory f, String s) {
        String what = null;
        List<SkillExpert> children = new ArrayList<SkillExpert>();
        int t = 0;
        while (t < s.length()) {
            char c = s.charAt(t);
            if (c == '(') {
                int cnt = 0;
                for (int y = t; t < s.length(); y++) {
                    char sc = s.charAt(y);
                    if (sc == '(') cnt++;
                    if (sc == ')') {
                        cnt--;
                        if (cnt == 0) {
                            children.add(parse(f, s.substring(t + 1, y)));
                            t = y;
                            break;
                        }
                    }
                }
            } else if (c == ')') {
                //
            } else if (c == '&') {
                what = "AND";
            } else if (c == '|') {
                what = "OR";
            } else if ((c == ',') && (s.indexOf(')') < 0)) {
                String skillName = s.substring(0, t);
                Experience exp = Experience.valueOf(s.substring(t + 1));
                return f.createMatchNode(skillName, exp);
            }
            t++;
        }
        if (what != null) {
            if ("AND".equals(what)) {
                return f.createAndNode(children.toArray(new SkillExpert[0]));
            } else if ("OR".equals(what)) {
                return f.createOrNode(children.toArray(new SkillExpert[0]));
            }
        }
        if (children.size() != 1) throw new RuntimeException("Unknown node " + s);
        return children.get(0);
    }

}
