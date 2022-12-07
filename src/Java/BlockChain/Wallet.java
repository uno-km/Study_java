package Java.BlockChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet
{
	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}
	public PublicKey getPublicKey()
	{
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey)
	{
		this.publicKey = publicKey;
	}
	public Wallet()
	{
		generateKeyPair();
	}
	
	public void generateKeyPair()
	{
		try
		{
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// 아래의 메서드로 초기화하고, 키 페어를 생성합니다!
			keyGen.initialize(ecSpec, random); // 256 바이트는 허용가능한 보안수준을 제공합니다!!!
			KeyPair keyPair = keyGen.generateKeyPair();
			// Set the public and private keys from the keyPair
			this.privateKey = keyPair.getPrivate();
			this.publicKey = keyPair.getPublic();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
