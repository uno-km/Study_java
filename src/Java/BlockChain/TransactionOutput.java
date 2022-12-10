package Java.BlockChain;

import java.security.PublicKey;

import lombok.Data;
@Data
public class TransactionOutput extends TransactionSuper
{
	private String id;
	private String parentTransactionId; // 생성된 트랜잭션의 ID
	
	public TransactionOutput(Transaction tr)
	{
		super.reciepient = tr.getReciepient();
		super.value = tr.getValue();
		this.parentTransactionId = tr.getTransactionId();
		this.id = BlockUtils.applySha256(BlockUtils.getStringFromKey(super.reciepient) + Float.toString(super.value) + parentTransactionId);
	}
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId)
	{
		super.reciepient = reciepient;
		super.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = BlockUtils.applySha256(BlockUtils.getStringFromKey(reciepient) + Float.toString(value) + parentTransactionId);
	}
	
	// 동전이 내껀지 확인
	public boolean isMine(PublicKey publicKey)
	{
		return (this.reciepient == publicKey);
	}
}
