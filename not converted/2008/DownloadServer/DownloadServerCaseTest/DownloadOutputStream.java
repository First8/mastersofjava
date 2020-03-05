import java.io.IOException;
import java.io.OutputStream;


public class DownloadOutputStream extends OutputStream {

	private long written=0;
	private long errorAt=-1;
	private Boolean closed;
	private int errorOn;
	
	public DownloadOutputStream(int errorOn) {
		this.errorOn=errorOn;
	}
	
	@Override
	public void write(int b) throws IOException {
		this.write(new byte[] { (byte)b });
		
	}
	@Override
	public void write(byte[] b) throws IOException {
		this.write(b,0,1);
	}
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		if (errorOn==1) throw new IOException("Write error.");
		long pos=written;
		for (int t=off;t<off+len;t++) {
			if (errorAt==-1) {
				if (b[t]!=(byte)pos) {
					errorAt=pos;
				}
			}
			pos++;
		}
		written=pos;
		delay();
	}
	
	@Override
	public void close() throws IOException {
		if ((errorOn!=2)&&(closed)) throw new IOException("Already closed.");
		closed=true;
		if (errorOn==2) throw new IOException("Write error.");
	}
	
	protected long getErrorAt() {
		return errorAt;
	}
	
	protected long getWritten() {
		return written;
	}
	
	protected boolean isClosed() {
		if (closed==null) return true;
		return closed;
	}
	
	private void delay() {
		try {
			Thread.sleep(1L);
		} catch (InterruptedException ex) {
			//
		}
	}
	
	protected void setClosed(boolean closed) {
		if (this.closed==null) {
			this.closed = closed;
		}
	}
	
}
