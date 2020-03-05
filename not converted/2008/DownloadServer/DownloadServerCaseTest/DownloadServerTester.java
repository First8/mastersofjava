import nl.moj.model.Tester;

public class DownloadServerTester implements Tester.Testable {
	
	private static final int[] ERROR=new int[] {
		0,0,0,
		1,2,3,
		4+0,4+6,
		0,0
	};
	private static final int[] ERROR_OUT=new int[] {
		0,0,0,
		0,0,0,
		0,0,
		1,2
	};

	private static final long[] SIZES=new long[] {
		8*1024,42,8*1024+768,
		8*1024,8*1024,8*1024,
		8*1024,8*1024,
		2*1024,4*1024
	};

	private static final long[] IDS=new long[] {
		42,43,44,
		42,43,44,
		42,43,
		45,46,
	};
	
	private static final String[] NAMES=new String[] {
			"TestCase #1",
			"TestCase #2",
			"TestCase #3",
			"Connection Loss #1",
			"Connection Loss #2",
			"Connection Loss #3",
			"Read error #1",
			"Read error #2",
			"Write error #1",
			"Write error #2"
			//
			// TODO: Add more tests.
			//
	};
	
	public int getTestCount() {
		return NAMES.length;
	}

	public String getTestName(int nr) {
		return NAMES[nr];
	}
	
	public String getTestDescription(int nr) {
		StringBuffer sb=new StringBuffer();
		sb.append("The DownloadServer receives a request to download the file with id "+IDS[nr]+"\n");
		sb.append("This file is "+SIZES[nr]+" bytes in size. ");
		if ((ERROR[nr]>0)||(ERROR_OUT[nr]>0)) {
			sb.append("The following error(s) will occur:\n");
			switch (ERROR[nr]) {
			case 0: break;
			case 1: sb.append("- The database connection will be lost at beginTransaction()\n");break;
			case 2: sb.append("- The database connection will be lost at getDownloadById()\n");break;
			case 3: sb.append("- The database connection will be lost at closeTransaction()\n");break;
			default: sb.append("- An exception will occur during reading.\n");break;					
			}
			switch (ERROR_OUT[nr]) {
			case 0: break;
			default: sb.append("- A exception will occur during writing.\n");break;
			}
		} else {
			sb.append("There will be no errors during the request.\n");
		}
		sb.append("\n");
		sb.append("Expected behavior:\n");
		if ((ERROR[nr]>0)||(ERROR_OUT[nr]>0)) {
			sb.append("- All used resources must be freed properly.\n");
			sb.append("- The Success property on the DownloadResponse must be false.\n");
		} else {
			sb.append("- The data must be copied exactly from the inputstream to the outputstream.\n");
			sb.append("- The copying must be implemented efficiently.\n");
			sb.append("- All used resources must be freed properly.\n");
			sb.append("- The Success property on the DownloadResponse must be true.\n");
		}
		//
		return sb.toString();
	}
	
	public boolean performTest(int nr) throws Throwable {
		//
		boolean result=true;
		//
		// Create a new Instance for each test.
		//
		try {
			DownloadManagerImpl mgr=new DownloadManagerImpl(ERROR[nr],SIZES[nr],IDS[nr]);
			DownloadServerImpl instance=new DownloadServerImpl(mgr);
			//
			DownloadRequestImpl req=new DownloadRequestImpl(IDS[nr]);
			DownloadResponseImpl resp=new DownloadResponseImpl(ERROR_OUT[nr]);
			//
			instance.onDownloadRequest(req,resp);
			//
			if ((ERROR[nr]==0)||(ERROR[nr]>3)) {
				if (mgr.isOpen()) {
					System.out.println("ERR : Manager is still in transaction.");
					result=false;
				}
			}
			//
			if (resp.getSuccess()==null) {
				System.out.println("ERR : Response does not contain a valid success value.");
				result=false;
			} else if (Boolean.TRUE.equals(resp.getSuccess())) {
				if (ERROR[nr]!=0||ERROR_OUT[nr]!=0) {
					System.out.println("ERR : Success is true, but there were errors.");
					result=false;
				}
			} else if (Boolean.FALSE.equals(resp.getSuccess())) {
				if (ERROR[nr]==0&&ERROR_OUT[nr]==0) {
					System.out.println("ERR : Success is false, but there should be no errors.");
					result=false;
				}
			}
			//
			if (ERROR[nr]==0&&ERROR_OUT[nr]==0) {
				long written=resp.getOutputStreamIntern().getWritten();
				long errorAt=resp.getOutputStreamIntern().getErrorAt();
				if (written!=SIZES[nr]) {
					System.out.println("ERR : Only "+written+" bytes were written to the output. Expected "+SIZES[nr]+" bytes.");
					result=false;
				}
				if (errorAt>=0) {
					System.out.println("ERR : CRC error at position "+errorAt);
					result=false;
				}
				//
			}
			//
			if (!resp.getOutputStreamIntern().isClosed()) {
				System.out.println("ERR : Output stream was not closed.");
				result=false;					
			}
			//
			if (mgr.getLastInputStream()!=null) {
				if (!mgr.getLastInputStream().isClosed()) {
					System.out.println("ERR : Input stream was not closed.");
					result=false;					
				}
			}
			//
		} catch (Exception ex) {
			//
			// Catch the exception, so that other tests may
			// still be executed. Do print the stacktrace. 
			//
			ex.printStackTrace();
			//
			return false;
		}
		//
		return result;
	}
	
}
