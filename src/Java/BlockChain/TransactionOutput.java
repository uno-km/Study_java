package Java.BlockChain;

import java.security.PublicKey;

import lombok.Data;
@Data
public class TransactionOutput
{
	private String id;
	private PublicKey reciepient; // 이 코인의 새 주인
	private float value; // 잔액
	private String parentTransactionId; // 생성된 트랜잭션의 ID
	
	// Constructor
	public TransactionOutput(Transaction tr)
	{
		this.reciepient = tr.getReciepient();
		this.value = tr.getValue();
		this.parentTransactionId = tr.getTransactionId();
		this.id = BlockUtils.applySha256(BlockUtils.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
	}
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
