import java.security.*;
import java.io.*;
import java.util.*;

public class SignAndVerify {

	public static void main(String[] args) throws Exception {

		// generate public and private keys for use with RSA
		KeyPairGenerator keyPairGenerator = 
			KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair kPair = keyPairGenerator.genKeyPair();

		// get instance of engine class Signature
		Signature signature = Signature.getInstance("SHA512withRSA");

		// init in sign mode with private key
		signature.initSign(kPair.getPrivate());

		// read message and update signature
		FileInputStream in = new FileInputStream(args[0]);
		byte[] buffer = new byte[1024];
		int nread;
		while ((nread = in.read(buffer)) != -1) 
		{
			signature.update(buffer, 0, nread);			
		}
		in.close();

		// generate signature
		byte[] fileSignature = signature.sign();

		// convert to base64 just to print a string
		Base64.Encoder encoder = Base64.getEncoder();
		System.out.println(encoder.encodeToString(fileSignature));

		Scanner scanner = new Scanner(System.in);
		System.out.println("Press ENTER to continue");
		scanner.nextLine();

		// init in verify mode with public key
		signature.initVerify(kPair.getPublic());
		// read message again
		in = new FileInputStream(args[0]);
		while ((nread = in.read(buffer)) != -1) 
		{
			signature.update(buffer, 0, nread);			
		}
		in.close();

		// verify message's signature
		boolean result = signature.verify(fileSignature);
		System.out.println(result);

	}

}