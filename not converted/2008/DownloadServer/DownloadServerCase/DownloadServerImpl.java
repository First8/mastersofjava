import java.io.InputStream;
import java.io.OutputStream;

/**
 * Tiz is ze doowlood sevice 
 * Yse tiz to get reel cool muzzik  
 * By Jogi. I em ze best. gtz to Neko 
 */

public class DownloadServerImpl {

	/** achitect suggsted tiz value but dunno wat too do wit it. */ 
	public static final int PREFERRED_BUFFER_SIZE=1024; 
	
	private DownloadManager mgr;
	
	public DownloadServerImpl(DownloadManager mgr) {
		 this.mgr=mgr;
	}
	
	public void onDownloadRequest(DownloadRequest request,DownloadResponse response) throws Exception {
		//
		// get ze stuff
		//
		mgr.beginTransaction();
		InputStream in=mgr.getDownloadById(request.getDownloadId());
		OutputStream out=response.getOutputStream();
		//
		// koppie oit (way be slow(
		//
		int read=0;
		while ((read=in.read())!=-1){
			out.write(read);
		};
		//
		// jop wel doone.
		//
		response.setSuccess(true);
		//
		// clien up
		//
		mgr.endTransaction();
		in.close();
		out.close();
		//
	}

}
