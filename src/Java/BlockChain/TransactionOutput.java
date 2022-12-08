package Java.BlockChain;

import java.security.PublicKey;

public class TransactionOutput
{
	private String id;
	private PublicKey reciepient; // 이 코인의 새 주인
	private float value; // 잔액
	private String parentTransactionId; // 생성된 트랜잭션의 ID
	
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
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public PublicKey getReciepient()
	{
		return reciepient;
	}
	
	public void setReciepient(PublicKey reciepient)
	{
		this.reciepient = reciepient;
	}
	
	public float getValue()
	{
		return value;
	}
	
	public void setValue(float value)
	{
		this.value = value;
	}
	
	public String getParentTransactionId()
	{
		return parentTransactionId;
	}
	
	public void setParentTransactionId(String parentTransactionId)
	{
		this.parentTransactionId = parentTransactionId;
	}
	
}
