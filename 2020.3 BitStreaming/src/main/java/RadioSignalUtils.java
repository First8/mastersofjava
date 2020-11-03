import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.function.Function;

public class RadioSignalUtils {

	/**
	 * Utility function to nicely print bits to console
	 * @param bits
	 */
	public static void printBits(byte[] bits) {
		System.out.print("Printing the bits ["+bits.length+"]=");
		for (int i = 0; i < bits.length; i++) {
			System.out.print(bits[i]);
			if (i % 8 == 7) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	/**
	 * Writes an input string of 0's and 1's to the output stream.
	 * @param outputStream
	 * @param binaryCharacters
	 */
	public static void writeRaw(OutputStream outputStream, String binaryCharacters) {
		try {
			byte[] total = new byte[binaryCharacters.length()];
			for (int i = 0; i < binaryCharacters.length(); i++) {
				char ch = binaryCharacters.charAt(i);
				boolean isOk = ch == '0' || ch == '1';
				if (!isOk ) {
					throw new RuntimeException("Error: invalid bytes found.");
				}
				byte b = 0;
				if (ch == '1') {
					b = 1;
				}
				total[i]=b;
			}
			outputStream.write(total);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** 
	 * Write an input string of 0's and 1's to the output stream, using biphase encoding.
	 * @param outputStream
	 * @param binaryCharacters
	 */

	public static void writeBiphase(OutputStream outputStream, String binaryCharacters) {
		try {
			byte prev = 0;
			binaryCharacters = binaryCharacters.replace(" ","");// remove spaces
			byte[] total = new byte[binaryCharacters.length()*2];
			for (int i = 0; i < binaryCharacters.length(); i++) {
				char ch = binaryCharacters.charAt(i);
				byte[] bytes = new byte[2];
				if (ch == '0') {
					if (prev == 1) {
						prev = 0;
					} else {
						bytes[0]=1;
						bytes[1]=1;
						prev = 1;
					}
				}  else {
					bytes[prev]=1;
				}
				total[2*i] = bytes[0];
				total[2*i+1] = bytes[1];
			}
			outputStream.write(total);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Transforms a String of 1's and 0's to a byte[]. Allows spaces to be used.
	 * @param in
	 * @return
	 */
	public static byte[] createBytesFromString(String in) {
		String t = in.replaceAll("[^01]", "");
		byte[] result = new byte[t.length()];
		for (int i = 0; i < t.length(); i++) {
			result[i] = t.charAt(i) == '0' ? (byte) 0 : (byte) 1;
		}
		return result;
	}

	
	/**
	 * Runs a test by hooking up a Piped Streams and executing the lambda.
	 * @param lambdaFunction the lamdbda to execute on the output stream
	 * @return
	 */
	public static RadioListener runStreamTransformation(Function<OutputStream, Void> lambdaFunction) {
		try {
			return new RadioSignalUtils().runStream(lambdaFunction);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void closeRadioStreamWhenEverythingRead(PipedInputStream inputStream ) {
		boolean isReadCompletely = false;
		try {
			do  {
				Thread.yield();
				// wait until input stream is read completely.
				isReadCompletely = inputStream.available()==0;
			} while (!isReadCompletely );

			inputStream.close(); // close when read completely
		} catch (Exception ex) {
			// do nothing
		}
	}
	private void closeRadioStreamWhenStillOpen(PipedInputStream inputStream ) {
		try {
			inputStream.close(); // ensures always closed
		} catch (Exception ex) {
			// do nothing
		}
	}

	/**
	 * A thread reads a stream of external messages.
	 * @param lambdaFunction on which an output stream can be applied.
	 * @return completed radio listener
	 * NB: streams are closed explicitly, to ensure correct memory usage.
	 */
	RadioListener runStream(Function<OutputStream, Void> lambdaFunction) {
		final RadioListener listener = new RadioListener();

		try {
			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream(inputStream);
			Thread thread = new Thread(() -> {
				System.out.println("START");
				listener.readStream(inputStream);
				closeRadioStreamWhenStillOpen(inputStream);
			});
			thread.start();
			lambdaFunction.apply(outputStream);
			outputStream.flush();
			closeRadioStreamWhenEverythingRead(inputStream);
            outputStream.close();
			thread.join();
			return listener;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
