/**
 * constructs skill experts of various types.
 */
public interface SkillExpertFactory {

    /**
     * creates an SkillExpert that only returns true if All its children return true.
     * @param childNodes the child SkillExperts.
     * @return a SkillExpert that performs the and operation.
     */
    public SkillExpert createAndNode(SkillExpert[] childNodes);

    /**
     * creates an SkillExpert that only returns true if One of its children return true.
     * @param childNodes the children.
     * @return a SkillExpert.
     */
    public SkillExpert createOrNode(SkillExpert[] childNodes);

    /**
     * creates an SkillExpert that only returns true if the resume contains an
     * entry that exactly matches the given skill and experience.
     * @param skillName the skill to look for.
     * @param exp the required experience.
     * @return the SkillExpert.
     */
    public SkillExpert createMatchNode(String skillName, Experience exp);

}
