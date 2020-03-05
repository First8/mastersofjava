import java.io.OutputStream;


public class DownloadResponseImpl implements DownloadResponse {

	private Boolean success=null;
	private DownloadOutputStream out;
	
	public DownloadResponseImpl(int outError) {
		out=new DownloadOutputStream(outError);
	}
	
	@Override
	public OutputStream getOutputStream() {
		out.setClosed(false);
		return out;
	}
	
	protected DownloadOutputStream getOutputStreamIntern() {
		return out;
	}

	@Override
	public void setSuccess(boolean success) {
		this.success=success;
	}
	
	public Boolean getSuccess() {
		return success;
	}

}
