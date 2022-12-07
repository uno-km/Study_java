package Java.BlockChain;

import java.security.PublicKey;

public class TransactionOutput
{
	public String id;
	public PublicKey reciepient; // 이 코인의 새 주인
	public float value; // 잔액
	public String parentTransactionId; // 생성된 트랜잭션의 ID
	
	// Constructor
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId)
	{
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = BlockUtils.applySha256(BlockUtils.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
	}
	
	// 동전이 내껀지 확인
	public boolean isMine(PublicKey publicKey)
	{
		return (publicKey == reciepient);
	}
}
