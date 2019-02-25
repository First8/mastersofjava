import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ResumeImpl implements Resume {

    private String name;
    private Map<String, Experience> attr = new HashMap<String, Experience>();

    public ResumeImpl(String parse) {
        String[] parts = cut(parse, "|");
        name = parts[0];
        for (int t = 1; t < parts.length; t++) {
            String[] skill = cut(parts[t], ",");
            attr.put(skill[0], Experience.valueOf(skill[1]));
        }
    }

    @Override
    public Experience getExperienceForSkill(String s) {
        return attr.get(s);
    }

    @Override
    public String[] getSkills() {
        return attr.keySet().toArray(new String[attr.size()]);
    }

    @Override
    public boolean hasSkill(String name) {
        return attr.containsKey(name);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Resume of '" + name + "'\n");
        for (String s : attr.keySet()) {
            sb.append(s + ": " + attr.get(s) + "\n");
        }
        return sb.toString();
    }

    private static String[] cut(String s, String sep) {
        List<String> l = new ArrayList<String>();
        //
        StringTokenizer st = new StringTokenizer(s, sep);
        while (st.hasMoreTokens()) {
            l.add(st.nextToken());
        }
        //
        return (String[]) l.toArray(new String[l.size()]);
    }
    
    @Override
    public String getName() {
        return name;
    }
}
