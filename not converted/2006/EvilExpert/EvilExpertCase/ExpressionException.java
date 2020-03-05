
/**
 * Thrown whenever there is an error in an expression. 
 * @author E. Expert
 */
public class ExpressionException extends Exception {

    private static final long serialVersionUID = 5159704095924620419L;

    public ExpressionException() {
    }

    public ExpressionException(String arg0) {
        super(arg0);
    }

    public ExpressionException(Throwable arg0) {
        super(arg0);
    }

    public ExpressionException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
