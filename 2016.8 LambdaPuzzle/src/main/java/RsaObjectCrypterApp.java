import java.util.List;

public class RsaObjectCrypterApp {

	private String[] INPUT = new String[]{"nljug event","encrypt sample","lorum ipsem"}; 
	
	public boolean checkEncryptAndDecrypt() throws Exception {
		LambdaPuzzleImpl puzzle = new LambdaPuzzleImpl(); 
		List<Integer> list = puzzle.primes(0, 1000);
		Integer lastPrime = list.get(list.size()-1); 
		
		RsaObjectCrypter crypter = new RsaObjectCrypter((""+lastPrime+lastPrime+lastPrime+lastPrime).getBytes(),(""+lastPrime+lastPrime).getBytes());
		
		boolean  result = true; 
		for (String item: INPUT) {
			byte[] encrypted  = crypter.encrypt(item); 
			
			Object decrypted  = crypter.decrypt(encrypted); 
			
			result = result && decrypted.equals(encrypted);
		}
		return result; 
	}
	
	public  static void main(String[] args) throws Exception {
		RsaObjectCrypterApp app = new RsaObjectCrypterApp(); 
		boolean checkEncryptAndDecrypt = app.checkEncryptAndDecrypt();
	}
}