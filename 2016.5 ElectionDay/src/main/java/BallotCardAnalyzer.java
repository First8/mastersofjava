/**
 * Analyzes ballot cards. 
 */
public interface BallotCardAnalyzer {

    /**
     * Analyzes the given ballot card and returns the corresponding result.
     * @param ballotCard the ballot card to analyse.
     * @return BLANK,INVALID,STAY or EXIT.
     */
    public Result analyze(String ballotCard);

}
