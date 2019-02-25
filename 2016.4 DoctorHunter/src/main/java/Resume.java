/**
 * A resume is a collection of skills and experience levels.
 */
public interface Resume {

    /**
     * @return the name of owner of the resume.
     */
    String getName();

    /**
     * @return all the skills.
     */
    String[] getSkills();

    /**
     * @param s the skill to get the experience for.
     * @return the experience for the specified skill.
     */
    Experience getExperienceForSkill(String s);

    /**
     * @param name the name of the skill.
     * @return true if the named skill exists in this resume.
     */
    boolean hasSkill(String name);

}
