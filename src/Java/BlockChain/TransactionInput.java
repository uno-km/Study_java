package Java.BlockChain;
public class TransactionInput
{
	public String transactionOutputId; // 트랜잭션 참조하기 위한 아이디
	public TransactionOutput UTXO; // 사용하지 않은 트랜잭션 출력
	
	public TransactionInput(String transactionOutputId)
	{
		this.transactionOutputId = transactionOutputId;
	}
}