import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngineManager;

import nl.moj.model.Tester;

public class EvilExpertTester implements Tester.Testable {
	
    static {
        // Preload scripting.
        new ScriptEngineManager().getEngineByName("js");
    }
    
	private static final String[] NAMES=new String[] {
	        "Unknown langauge",
	        "Bad Script",
	        "Static Eval",
	        "Static Eval",
			"Nr Eval",
			"Nr Eval",
			"String Eval",
			"String Eval",
			"Boolean Eval",
			"Boolean Eval",
			"Combined Eval",
			"Combined Eval",
			"result Eval",
			"result Eval",
			"Secret"
	};
	
	private static final String[] LANGUAGES=new String[] {
	    "xyzof","js",
	    "js","js",
        "js","js",
        "js","js",
        "js","js",
        "js","js",
        "js","js",
        "js"
	};
	
	private static final String[] EXPRESSION=new String[] {
	    "","x=y+-295 khdsd ;eio2i\"&*()",
	    "true;",
	    "false;",
	    "(a==42);",
        "(a==42);",
	    "(s=='Pindakaas');",
        "(s=='Pindakaas');",
	    "(!b);",
        "(!b);",
	    "((a==42)&&(!b)&&(s=='Yo!'));",
        "((a==42)&&(!b)&&(s=='Yo!'));",
        "result=((a==42)&&(!b)&&(s=='Yo!'));b=false;",
	    "result=((a==42)&&(!b)&&(s=='Yo!'));b=false;",
        "(a==21);"	    
	};
	
	private static final Boolean[] RESULTS=new Boolean[] {
	   null,null,
	   true,false,
	   true,false,
	   true,false,
	   true,false,
	   true,false,
	   true,false,
	   true
	}; 
	
	private static final Object[][] VARS=new Object[][] {
	    { },{ },
	    { },{ },
	    { "a",42 }, {"a",21 },
	    { "s","Pindakaas" },{ "s","PindaCheese" },
	    { "b", false }, { "b", true },
	    { "a",42,"b",false,"s","Yo!"},{ "a",42,"b",false,"s","Who?" },
        { "a",42,"b",false,"s","Yo!"},{ "a",42,"b",false,"s","Who?" },
        { "a",21 }
	};
	
	public int getTestCount() {
		return NAMES.length-1;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		if (nr<2) {
		    sb.append("Attempt to e");
		} else {
		    sb.append("E");
		}
		sb.append("valuate the expression \'"+EXPRESSION[nr]+"' in language '"+LANGUAGES[nr]+"'.\n");
		if (VARS[nr].length>0) {
		    sb.append("The following variables are set:\n");
	        for (int t=0;t<VARS[nr].length/2;t++) {
	            if (t>0) sb.append(", ");
	            sb.append(String.valueOf(VARS[nr][t*2])+"='"+VARS[nr][t*2+1]+"'");
	        }
	        sb.append("\n");
		}
		if (nr>=12) {
		    sb.append("The result of the script is not the result of the latest statement but the value assigned to the 'result' variable.\n");
		}
        sb.append("\n");
		//
		sb.append("Expected result : ");
		if (RESULTS[nr]==null) {
		    sb.append("the throwing of an ExpressionException.");
		} else {
		    sb.append(RESULTS[nr]);
		}
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		// Assume the worst
		//
		boolean result=false;
		//
		// Create a new Instance for each test.
		//
		try {
		    ScriptExpressionEvaluator instance=new ScriptExpressionEvaluator(LANGUAGES[nr]);
		    boolean out=instance.evaluate(EXPRESSION[nr], toMap(VARS[nr]));
		    if (RESULTS[nr]==null) {
		        System.out.println("Expected an ExpressionException but your result was "+out);
		    } else {
	            if (out!=RESULTS[nr]) {
	                System.out.println("Your result was : "+out+". Expected was "+RESULTS[nr]);
	            } else {
	                result=true;
	            }
		    }
		} catch (ExpressionException ex) {
            if (RESULTS[nr]!=null) { 
                ex.printStackTrace();
                return false;
            } else {
                return true;
            }
		} catch (Exception ex) {
		    ex.printStackTrace();
		    return false;
		}
		//
		return result;
	}
	
	private Map<String,Object> toMap(Object[] obj) {
	    Map<String,Object> results=new HashMap<String, Object>();
	    for (int t=0;t<obj.length/2;t++) {
	        results.put(String.valueOf(obj[t*2]),obj[t*2+1]);
	    }
	    return results;
	}
	
}
