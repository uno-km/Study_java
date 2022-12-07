package Java.BlockChain;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction
{
	public float value;
	public PublicKey sender; // 보낸사람의 공개키가 저장됨, 솔리디티 공부하면 대강 느껴짐
	public PublicKey reciepient; // 받는사람의 공개키
	public String transactionId; // 이것은 트랜잭션의 해시값이기도 합니다.
	public byte[] signature; // 다른 사람들로부터 이 지갑과 거래에 대하것을 방어하기 위함
	private int sequence = 0; // 얼마나 많은 트랜잭션이 생성되었는지 대략적으로 계산하기위한 변수
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs)
	{
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	// 트랜잭션 해시(ID로 사용)를 계산
	private String calulateHash()
	{
		this.sequence++; // 두 개의 동일한 트랜잭션을 방지하기 위해 시퀀스를 변경
		return BlockUtils.applySha256(BlockUtils.getStringFromKey(this.sender) + BlockUtils.getStringFromKey(this.reciepient) + Float.toString(this.value) + this.sequence);
	}
	// 서명을 하게한다.
	public void generateSignature(PrivateKey privateKey)
	{
		String data = BlockUtils.getStringFromKey(this.sender) + BlockUtils.getStringFromKey(this.reciepient) + Float.toString(this.value);
		this.signature = BlockUtils.applyECDSASig(privateKey, data);
	}
	// 서명한 데이터가 변조되진 않았는지 검사한다.
	public boolean verifiySignature()
	{
		String data = BlockUtils.getStringFromKey(this.sender) + BlockUtils.getStringFromKey(this.reciepient) + Float.toString(this.value);
		return BlockUtils.verifyECDSASig(this.sender, data, this.signature);
	}
}
