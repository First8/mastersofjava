import java.io.InputStream;


public class DownloadManagerImpl implements DownloadManager {

	private int error;
	private long size; 
	private long id;
	private DownloadInputStream lastInputStream;
	
	public DownloadManagerImpl(int error,long size,long id) {
		this.error=error;
		this.size=size;
		this.id=id;
	}
	
	private boolean open;
	
	@Override
	public synchronized void beginTransaction() throws ManagerException {
		if (error==1) throw new ManagerException("Database connection lost.");
		if (open) throw new ManagerException("Transaction is already begun.");
		open=true;
	}

	@Override
	public synchronized void endTransaction() throws ManagerException {
		if (error==3) throw new ManagerException("Database connection lost.");
		if (!open) throw new ManagerException("Transaction has not started.");
		open=false;
	}

	@Override
	public synchronized InputStream getDownloadById(long id) throws ManagerException {
		if (error==2) throw new ManagerException("Database connection lost.");
		if (this.id!=id) throw new ManagerException("Unknown download id "+id);
		lastInputStream=new DownloadInputStream(size,error-3);
		return lastInputStream;
	}
	
	protected boolean isOpen() {
		return open;
	}
	
	protected DownloadInputStream getLastInputStream() {
		return lastInputStream;
	}

}
