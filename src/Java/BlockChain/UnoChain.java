package Java.BlockChain;

import java.security.Security;

public class UnoChain
{
	
	public static void main(String[] args)
	{
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// 새지갑 2개 정도 생성 하나는 보내는이, 받는이 
		Wallet walletA = new Wallet();
		Wallet walletB = new Wallet();
		System.out.println("Private and public keys:");
		System.out.println(BlockUtils.getStringFromKey(walletA.getPrivateKey()));
		System.out.println(BlockUtils.getStringFromKey(walletA.getPublicKey()));
		Transaction transaction = new Transaction(walletA.getPublicKey(), walletB.getPublicKey(), 5, null);
		transaction.generateSignature(walletA.getPrivateKey());
		System.out.println("Is signature verified");
		System.out.println(transaction.verifiySignature());
	}
}
