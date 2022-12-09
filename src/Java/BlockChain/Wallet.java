package Java.BlockChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet
{
	private PrivateKey privateKey;
	private PublicKey publicKey;
	// 이 지갑이 소유한 UTXO만 보관
	public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
	
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
	// 잔액을 반환하고 이 지갑이 소유한 UTXO를 저장
	public float getBalance(HashMap<String, TransactionOutput> UTXOArr)
	{
		float total = 0;
		for (Map.Entry<String, TransactionOutput> item : UTXOArr.entrySet())
		{
			TransactionOutput UTXO = item.getValue();
			if (UTXO.isMine(publicKey))
			{
				// 코인이 내 것인 경우 사용되지 않은 거래 목록에 추가
				UTXOs.put(UTXO.getId(), UTXO);
				total += UTXO.getValue();
			}
		}
		return total;
	}
	// 이 지갑에서 새 트랜잭션을 생성하고 반환
	public Transaction sendFunds(HashMap<String, TransactionOutput> UTXOArr, PublicKey _recipient, float value)
	{
		if (getBalance(UTXOArr) < value)
		{ // 잔금을 모음
			System.out.println("#Not Enough funds to send transaction. Transaction Discarded.");
			return null;
		}
		// 입력 배열 목록 생성
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		float total = 0;
		for (Map.Entry<String, TransactionOutput> item : UTXOs.entrySet())
		{
			TransactionOutput UTXO = item.getValue();
			total += UTXO.getValue();
			inputs.add(new TransactionInput(UTXO.getId()));
			if (total > value) break;
		}
		Transaction newTransaction = new Transaction(publicKey, _recipient, value, inputs);
		newTransaction.generateSignature(privateKey);
		for (TransactionInput input : inputs)
		{
			UTXOs.remove(input.getTransactionOutputId());
		}
		return newTransaction;
	}
}
