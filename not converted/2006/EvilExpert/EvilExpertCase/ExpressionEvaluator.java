import java.util.Map;

/**
 * The Expression Evaluator brings 'Power to the User' by allowing an expression to override 
 * business logic. 
 * @author E. Expert
 */

public interface ExpressionEvaluator {

    /**
     * Evaluates the given expression.
     * @param expression the expression to evaluate.
     * @param variables a map holding name-value pairs containing any variables used in the expression.
     * @return the result of the expression : true or false.
     * @throws ExpressionException if there is an error in the expression.
     */
    public boolean evaluate(String expression,Map<String,Object> variables) throws ExpressionException;
    
}
