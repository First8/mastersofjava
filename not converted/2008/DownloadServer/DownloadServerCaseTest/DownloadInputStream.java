import java.io.IOException;
import java.io.InputStream;


public class DownloadInputStream extends InputStream {

	private static final int BLOCKSIZE=1024;
	private static final byte[] REF=new byte[BLOCKSIZE*2];
	
	static {
		for (int t=0;t<REF.length;t++) {
			REF[t]=(byte)t;
		}
	}
	
	private long position;
	private long size;
	private int error;
	private boolean closed;
	
	public DownloadInputStream(long size,int error) {
		this.size=size;
		this.error=error;
	}
	
	@Override
	public int read() throws IOException {
		byte[] b=new byte[1];
		int nr=read(b,0,1);
		if (nr<1) return -1;
		return ((int)b[0])&0x00FF;
	}
	@Override
	public int read(byte[] b) throws IOException {
		return read(b,0,b.length);
	}
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		if (closed) throw new IOException("Closed.");
		if ((error>=0) && (--error==0)) throw new IOException("Read Error");
		long ln=len;
		if (position+ln>=size) {
			ln=size-position;
		} 
		if (ln>BLOCKSIZE) {
			ln=BLOCKSIZE;
		}
		if (ln<=0) return -1;
		//
		delay();
		//
		System.arraycopy(REF,(int)(position%BLOCKSIZE), b, off,(int)ln);
		position=position+ln;
		//
		return (int)ln;
		//
	}
	@Override
	public int available() throws IOException {
		if (closed) throw new IOException("Closed.");
		long l=BLOCKSIZE;
		if (position+l>=size-1) {
			l=(size-1)-position;
		} 
		if (l<=0) return -1;
		return (int)l;
	}
	
	@Override
	public void close() throws IOException {
		if (closed) throw new IOException("Already closed.");
		closed=true;
	}
	
	protected boolean isClosed() {
		return closed;
	}
	
	private void delay() {
		try {
			Thread.sleep(1L);
		} catch (InterruptedException ex) {
			//
		}
	}
	
}
