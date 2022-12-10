package Java.BlockChain;

import java.security.PublicKey;

import lombok.Data;
@Data
public class TransactionSuper
{
	protected float value;
	protected PublicKey sender; // 보낸사람의 공개키가 저장됨, 솔리디티 공부하면 대강 느껴짐
	protected PublicKey reciepient; // 받는사람의 공개키
}
