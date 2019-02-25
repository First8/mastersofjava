/**
 * The Skill Expert matches a Resume to some requirements. These requirements
 * are defined outside this interface.
 */
public interface SkillExpert {

    /**
     * @param r the resume to match.
     * @return true if the resume matches the requirements.
     */
    public boolean matches(Resume r);

}
