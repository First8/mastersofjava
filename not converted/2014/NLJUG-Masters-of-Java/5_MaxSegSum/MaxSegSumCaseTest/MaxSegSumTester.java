import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import nl.moj.model.HasWorkspace;
import nl.moj.model.Tester;
import nl.moj.model.Workspace;


public class MaxSegSumTester implements Tester.Testable, HasWorkspace {
	
	private Workspace.Internal workspace;
	
	protected static interface Test {
		
		String getName();
		
		String getDescription();
		
		boolean isSuccess() throws Throwable;
		
	}
	
	protected final Test validResultTest = new Test(){
		
		private final int rounds = 3;
		
		private final int inputSize = 100;
		
		private BigDecimal maxSegSum(final Iterator<BigDecimal> amounts) {
	        BigDecimal s = BigDecimal.ZERO, r = BigDecimal.ZERO;
	        while (amounts.hasNext()) {
	            s = s.add(amounts.next()).max(BigDecimal.ZERO);
	            r = r.max(s);
	        }
	        
	        return r;
	    }
		
		private List<BigDecimal> createAmounts(){
			List<BigDecimal> retval = new ArrayList<>(inputSize);
			while(retval.size()<inputSize){
				final double d = Math.random();
				final BigDecimal bd = BigDecimal.valueOf(Math.round(10000 * d));
				retval.add(d<0.5 ? bd.negate() : bd);
			}
			return retval;
		}
		
		@Override
		public String getName() {
			return "valid result test";
		}

		@Override
		public String getDescription() {
			return "tests that your implementation provides the correct answer";
		}

		@Override
		public boolean isSuccess() throws Throwable {
			final MaxSegSumImpl impl = new MaxSegSumImpl();
			for(int round=0; round<rounds; round++){
				final List<BigDecimal> amounts = createAmounts();
				final BigDecimal correctAnswer = this.maxSegSum(amounts.iterator());
				final BigDecimal implAnswer = impl.maxSegSum(amounts.iterator());
				if(!correctAnswer.equals(implAnswer)){
					return false;
				}
			}
			return true;
		}
		
	};
	
	protected final Test declaresNoFieldsTest = new Test(){

		public String getName() {
			return "no fields test";
		}

		public String getDescription() {
			return "tests that your solution declares no fields";
		}

		public boolean isSuccess() throws Throwable {
			return MaxSegSumImpl.class.getDeclaredFields().length==0;
		}

	};

	protected final Test declaresSingleMethodTest = new Test(){
		@Override
		public String getName() {
			return "single method test";
		}

		@Override
		public String getDescription() {
			return "tests that your solution declares exactly one method";
		}

		@Override
		public boolean isSuccess() throws Throwable {
			return MaxSegSumImpl.class.getDeclaredMethods().length==1;
		}
	};
	
	protected final Test noThreadingTest = new Test(){

		public String getName() {
			return "no Threading test";
		}

		public String getDescription() {
			return 	"tests that your solution uses no threading";
		}

		public boolean isSuccess() throws Throwable {
			final String source = workspace.getContents(MaxSegSumImpl.class.getSimpleName() + ".java");
			return source.split("Thread|Runnable|Callable").length<2;
		}
		
	};
	
	protected final Test singleLoopTest = new Test(){
		
		public String getName() {
			return "single loop test";
		}

		public String getDescription() {
			return 	"tests that your solution's source-code contains at most one loop.\n" + 
					"do NOT use keywords 'do', 'for', or 'while' as part of your variable names, or this test yields a false positive.";
		}

		public boolean isSuccess() throws Throwable {
			final String source = workspace.getContents(MaxSegSumImpl.class.getSimpleName() + ".java");
			return source.split("while|for").length <=2;
		}
		
	};
	
	protected final List<Test> tests = Arrays.asList(validResultTest, declaresNoFieldsTest,declaresSingleMethodTest,noThreadingTest,singleLoopTest);
	
	public Workspace.Internal getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace.Internal workspace) {
		this.workspace = workspace;
	}
	
	public int getTestCount() {
		return tests.size();
	}

	public String getTestName(int nr) {
		return tests.get(nr).getName();
	}
	
	public String getTestDescription(int nr) {
		return tests.get(nr).getDescription();
	}
	
	public boolean performTest(int nr) throws Throwable {
		return tests.get(nr).isSuccess();
	}
}
