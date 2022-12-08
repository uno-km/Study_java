package Java.BlockChain;
public class TransactionInput
{
	private String transactionOutputId; // 트랜잭션 참조하기 위한 아이디
	private TransactionOutput UTXO; // 사용하지 않은 트랜잭션 출력
	
	public TransactionInput(String transactionOutputId)
	{
		this.transactionOutputId = transactionOutputId;
	}
	
	public String getTransactionOutputId()
	{
		return transactionOutputId;
	}
	
	public void setTransactionOutputId(String transactionOutputId)
	{
		this.transactionOutputId = transactionOutputId;
	}
	
	public TransactionOutput getUTXO()
	{
		return UTXO;
	}
	
	public void setUTXO(TransactionOutput uTXO)
	{
		UTXO = uTXO;
	}
	
}