import java.util.Map;

/**
 * Actual implementation of the Expression Evaluator. 
 */

public class ScriptExpressionEvaluator implements ExpressionEvaluator {

    /** 
     * This is the name of the special result variable that may be used in
     * a more complex script to hold the result of the expression.
     */
    private static final String RESULT="result";
    
    /**
     * constructs a new ScriptExpressionEvaluator.
     * @param shortName the name of the scripting language.
     * @throws ExpressionException if the scripting language is unknown.
     */
    public ScriptExpressionEvaluator(String shortName) throws ExpressionException {
        //
        // Implement Here
        //
    }
    
    /**
     * Evaluates the given expression.
     * @param expression the expression to evaluate.
     * @param variables a map holding name-value pairs containing any variables used in the expression.
     * @return the result of the expression : true or false.
     * @throws ExpressionException if there is an error in the expression.
     */
	public boolean evaluate(String expression, Map<String, Object> variables) throws ExpressionException {
	    //
	    // Implement Here
	    //
	    return false;
	}

}

