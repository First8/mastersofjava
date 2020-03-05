public class ResumeHunterImpl implements SkillExpertFactory {

    /**
     * creates an SkillExpert that only returns true if the resume contains an
     * entry that Exactly matches the given skill and experience.
     * @param skillName the skill to look for.
     * @param exp the required experience.
     * @return the SkillExpert that exactly matches the skill and experience.
     */
    public SkillExpert createMatchNode(String skillName, Experience exp) {
        //
        // Implement Here
        //
        return null;
    }

    /**
     * creates an SkillExpert that only returns true if All its children return true.
     * @param childNodes the child SkillExperts.
     * @return a SkillExpert that performs the and operation.
     */
    public SkillExpert createAndNode(SkillExpert[] childNodes) {
        //
        // Implement Here.
        //
        return null;
    }

    /**
     * creates an SkillExpert that only returns true if One of its children returns true.
     * @param childNodes the child SkillExperts.
     * @return a SkillExpert that performs the or operation.
     */
    public SkillExpert createOrNode(SkillExpert[] childNodes) {
        //
        // Implement Here
        //
        return null;
    }    

    /**
     * Matches a single resume to a skill expert.
     * @param x the skill expert.
     * @param r the resume.
     * @return true if the SkillExpert thinks the resume matches.
     */
    public boolean matches(SkillExpert x, Resume r) {
        return x.matches(r);
    }

}
