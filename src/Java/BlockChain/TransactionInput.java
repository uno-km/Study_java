package Java.BlockChain;

import lombok.Data;

@Data
public class TransactionInput
{
	private String transactionOutputId; // 트랜잭션 참조하기 위한 아이디
	private TransactionOutput UTXO; // 사용하지 않은 트랜잭션 출력
	
	public TransactionInput(String transactionOutputId)
	{
		this.transactionOutputId = transactionOutputId;
	}
	
}