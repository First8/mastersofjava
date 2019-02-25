public class DoctorHunterImpl implements SkillExpertFactory {

    public SkillExpert createAndNode(final SkillExpert[] childNodes) {
        return new SkillExpert() {
            @Override
            public boolean matches(Resume r) {
                for (int t = 0; t < childNodes.length; t++) {
                    if (!childNodes[t].matches(r)) return false;
                }
                return true;
            }
        };
    }

    public SkillExpert createOrNode(final SkillExpert[] childNodes) {
        return new SkillExpert() {
            @Override
            public boolean matches(Resume r) {
                for (int t = 0; t < childNodes.length; t++) {
                    if (childNodes[t].matches(r)) return true;
                }
                return false;
            }
        };
    }

    public SkillExpert createMatchNode(final String skillName, final Experience exp) {
        return new SkillExpert() {
            @Override
            public boolean matches(Resume r) {
                if (!r.hasSkill(skillName)) return false;
                return exp.equals(r.getExperienceForSkill(skillName));
            }
        };
    }

    public boolean matches(SkillExpert x, Resume r) {
        return x.matches(r);
    }

}

