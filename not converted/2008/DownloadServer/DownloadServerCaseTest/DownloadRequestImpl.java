
public class DownloadRequestImpl implements DownloadRequest {

	private long id;
	
	public DownloadRequestImpl(long id) {
		this.id=id;
	}
	@Override
	public long getDownloadId() {
		return id;
	}

}
