/**
 * Analyzes ballot cards. 
 */
public interface BallotCardAnalyzer {

    /**
     * Analyzes the given ballot card and returns the corresponding result.
     * @param ballotCard the ballot card to analyse.
     * @return BLANK,INVALID,MCCAIN or OBAMA.
     */
    public Result analyze(String ballotCard);

}
