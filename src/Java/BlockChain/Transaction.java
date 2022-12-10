package Java.BlockChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;
@Data
public class Transaction extends TransactionSuper
{
	private String transactionId; // 이것은 트랜잭션의 해시값이기도 합니다.
	private byte[] signature; // 다른 사람들로부터 이 지갑과 거래에 대하것을 방어하기 위함
	private int sequence = 0; // 얼마나 많은 트랜잭션이 생성되었는지 대략적으로 계산하기위한 변수
	private ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	private ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	public Transaction(Wallet sender, Wallet reciepient, float value)
	{
		super.sender = sender.getPublicKey();
		super.reciepient = reciepient.getPublicKey();
		super.value = value;
	}
	public Transaction(PublicKey sender, PublicKey reciepient, float value, ArrayList<TransactionInput> inputs)
	{
		super.sender = sender;
		super.reciepient = reciepient;
		super.value = value;
		this.inputs = inputs;
	}
	// 트랜잭션 해시(ID로 사용)를 계산
	private String calulateHash()
	{
		this.sequence++; // 두 개의 동일한 트랜잭션을 방지하기 위해 시퀀스를 변경
		return BlockUtils.applySha256(BlockUtils.getStringFromKey(super.sender, super.reciepient) + Float.toString(super.value) + this.sequence);
	}
	// 서명을 하게한다.
	public void generateSignature(PrivateKey privateKey)
	{
		String data = BlockUtils.getStringFromKey(super.sender, super.reciepient) + Float.toString(super.value);
		this.signature = BlockUtils.applyECDSASig(privateKey, data);
	}
	// 서명한 데이터가 변조되진 않았는지 검사한다.
	public boolean verifiySignature()
	{
		String data = BlockUtils.getStringFromKey(super.sender, super.reciepient) + Float.toString(super.value);
		return BlockUtils.verifyECDSASig(super.sender, data, this.signature);
	}
	
	public boolean processTransaction(HashMap<String, TransactionOutput> UTXOs, float minimumTransaction)
	{
		if (verifiySignature() == false)
		{
			System.out.println("#Transaction Signature failed to verify");
			return false;
		}
		// 트랜잭션 입력을 수집 (사용되지 않았는지 확인)
		for (TransactionInput i : this.inputs)
		{
			i.setUTXO(UTXOs.get(i.getTransactionOutputId()));
		}
		// 트랜잭션이 유효한지 확인
		if (getInputsValue() < minimumTransaction)
		{
			System.out.println("#Transaction Inputs to small: " + getInputsValue());
			return false;
		}
		// 트랜잭션 출력 생성
		float leftOver = getInputsValue() - this.value;
		// 입력값을 받은 다음 변화
		this.transactionId = calulateHash();
		// 수신자에게 값을 보내다
		this.outputs.add(new TransactionOutput(super.reciepient, super.value, this.transactionId));
		// 잔돈을 보낸 사람에게 돌려주다
		this.outputs.add(new TransactionOutput(super.sender, leftOver, this.transactionId)); //
		// 사용 안 함 목록에 출력
		for (TransactionOutput o : this.outputs)
		{
			UTXOs.put(o.getId(), o);
		}
		// UTXO 목록에서 사용된 트랜잭션 입력을 제거
		for (TransactionInput i : this.inputs)
		{
			// 트랜잭션을 찾을 수 없는 경우 건너뜀
			if (i.getUTXO() == null)
			{
				continue;
			}
			UTXOs.remove(i.getUTXO().getId());
		}
		return true;
	}
	
	// 입력 합계(UTXO) 값을 반환합니
	public float getInputsValue()
	{
		float total = 0;
		for (TransactionInput i : this.inputs)
		{
			// 트랜잭션을 찾을 수 없는 경우 건너뜁니다.
			if (i.getUTXO() == null)
			{
				continue;
			}
			total += i.getUTXO().getValue();
		}
		return total;
	}
	
	// 출력의 합계를 반환
	public float getOutputsValue()
	{
		float total = 0;
		for (TransactionOutput o : this.outputs)
		{
			total += o.getValue();
		}
		return total;
	}
}
