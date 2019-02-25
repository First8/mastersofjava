import java.util.ArrayList;
import java.util.List;

import nl.moj.model.Tester;

public class DoctorHunterTester implements Tester.Testable {

	private static final String[] NAMES = new String[] { "Single Match",
			"And Match", "Or Match", "Or And Match", "And Or Match",
			"Complex Match", "No Match" };

	private static final String[] CRITERIA = new String[] {
			"(Physiotherapy,NOVICE)",
			"(Physiotherapy,SKILLED)&(Psychology,EXPERT)",
			"(Surgery,NOVICE)|(Physiotherapy,NOVICE)|(Psychology,NOVICE)",
			"((Physiotherapy,EXPERT)|(Surgery,EXPERT))&(Psychology,EXPERT)",
			"((Physiotherapy,NOVICE)&(Psychology,NOVICE))|(Surgery,GURU)",
			"(Physiotherapy,EXPERT)&(Psychology,EXPERT)",
			// "(((Physiotherapy,EXPERT)|(Physiotherapy,GURU))&((Surgery,NOVICE)|(Surgery,SKILLED)|(Surgery,EXPERT)|(Surgery,GURU))&((Psychology,NOVICE)|(Psychology,SKILLED)|(Psychology,EXPERT)))",
			"(Management,EXPERT)" };

	private static final String[] RESUMES = new String[] {
			"JrDeJunior|Physiotherapy,NOVICE|Surgery,NOVICE|Psychology,NOVICE",
			"dr Anthony|Physiotherapy,SKILLED|Surgery,NOVICE|Psychology,EXPERT",
			"dr Harmsen|Physiotherapy,EXPERT|Surgery,NOVICE|Psychology,EXPERT",
			"dr P|Physiotherapy,EXPERT|Surgery,SKILLED|Psychology,EXPERT",
			"dr Dolittle|Surgery,GURU",
			// / "G.Guru|Physiotherapy,GURU|Surgery,EXPERT|Psychology,EXPERT",
			"dr Bob|Physiotherapy,EXPERT|Psychology,EXPERT" };

	private static final boolean[][] RESULTS = new boolean[][] {
			{ true, false, false, false, false, false },
			{ false, true, false, false, false, false },
			{ true, true, true, false /*faultcode 2-3*/ , false, false },
			{ false, false, true/*faultcode 3-2*/, true, false, true },
			{ true, false, false, false, true, false },
			{ false, false, true, true, false, true },
			{ false, false, false, false, false, false }, };

	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}

	public boolean[] getResultRow(int nr) {
		return RESULTS[nr];
	}

	public List<Boolean> toList(boolean[] row) {
		List<Boolean> ret = new ArrayList<>();

		for (boolean item : row) {
			ret.add(item);
		}

		return ret;
	}

	public String getTestDescription(int nr) {
		//System.out.println("getTestDescription " + nr + " - " + RESUMES.length
		//		+ " " + CRITERIA[nr]);
		StringBuffer feedback = new StringBuffer();
		feedback.append("In your local hospital are " + RESUMES.length
				+ " resumes of skilled individuals.\n");
		feedback.append("You are looking for someone matching the following criteria:\n");
		feedback.append(CRITERIA[nr]);
		feedback.append("\nApply the resumes to the criteria and find the matching resumes.\n");
		feedback.append("\nExpected results:\n");
		for (int t = 0; t < RESUMES.length; t++) {
			StringBuffer resumeLine = new StringBuffer();
			ResumeImpl r = new ResumeImpl(RESUMES[t]);
			boolean[] row = getResultRow(nr);
			// System.out.println(t + "-" +toList(row) + " " +getTestName(nr) );
			resumeLine.append(row[t] ? "matches : " : "no match: ");
			resumeLine.append(r.getName());
			while (resumeLine.length() < 27)
				resumeLine.append(" ");
			for (String s : r.getSkills()) {
				resumeLine.append(" " + s + "=" + r.getExperienceForSkill(s));
			}
			// System.out.println(t + "-" +toList(row) + " " +getTestName(nr) +
			// " resumeLine " +resumeLine);
			resumeLine.append("\n");
			feedback.append(resumeLine.toString());

		}
		//
		return feedback.toString();
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
			DoctorHunterImpl instance = new DoctorHunterImpl();

			SkillExpert skillExpertFactory = parse(instance, CRITERIA[nr]);
			boolean matches = false;
			StringBuffer resumeView = new StringBuffer();
			for (int t = 0; t < RESUMES.length; t++) {
				resumeView.append("Resume " + t + " - " + RESUMES[t] + "  \n");

				Resume r = new ResumeImpl(RESUMES[t]);
				matches = instance.matches(skillExpertFactory, r);
				if (matches != RESULTS[nr][t]) {
					System.err.println("The resume of '" + r.getName()
							+ "' should " + (RESULTS[nr][t] ? "" : "not")
							+ " have matched criteria (faultcode " +nr + "-"+ t  + "):");
					System.err.println(CRITERIA[nr]);
					System.err.println(r);
					result = false;
				}

			}
			if (result) {
				// resumeView
				System.out.println(resumeView);
				//System.out.println("CRITERIA " + nr + " - " + CRITERIA[nr]
				//		+ " (" + RESUMES.length + ")=" + result);
			} else {
				System.err.println(resumeView);
				//System.err.println("CRITERIA " + nr + " - " + CRITERIA[nr]
				//		+ " (" + RESUMES.length + ")=" + result);
			}

		} catch (Exception ex) {
			System.out
					.println("An exception is thrown. More peanuts are needed.");
			ex.printStackTrace();
			result = false;
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
					if (sc == '(')
						cnt++;
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
		if (children.size() != 1)
			throw new RuntimeException("Unknown node " + s);
		return children.get(0);
	}

}
