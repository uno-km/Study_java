package Java.BlockChain;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

public class UnoChain
{
	public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
	static int difficulty = BlockUtils.DIFFICULTY_NOMAL;
	public static void main(String[] args)
	{
		UnoChain uc = new UnoChain();
		uc.start();
	}
	
	public void start()
	{
		// 사용하지 않은 모든 트랜잭션 목록
		ArrayList<Block> blockchain = new ArrayList<Block>();
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Wallet walletA = new Wallet();
		Wallet walletB = new Wallet();
		Wallet coinbase = new Wallet();
		// 100 으노코인을 지갑으로 보내는 비트코인 트랜잭션을 만듭니다
		Transaction bitCoinTransaction = new Transaction(coinbase.getPublicKey(), walletA.getPublicKey(), 100f, null);
		// 생성 트랜잭션에 수동으로 서명
		bitCoinTransaction.generateSignature(coinbase.getPrivateKey());
		// 트랜잭션 ID 수동 설정
		bitCoinTransaction.setTransactionId("0");
		// 트랜잭션 출력 수동 추가
		bitCoinTransaction.getOutputs().add(new TransactionOutput(bitCoinTransaction.getReciepient(), bitCoinTransaction.getValue(), bitCoinTransaction.getTransactionId()));
		// 첫 번째 트랜잭션을 UTXO 목록에 저장하는 것이 중요
		UTXOs.put(bitCoinTransaction.getOutputs().get(0).getId(), bitCoinTransaction.getOutputs().get(0));
		System.out.println("Creating and Mining Genesis block... ");
		Block bitCoin = new Block("0");
		bitCoin.addTransaction(this, bitCoinTransaction, difficulty);
		BlockUtils.addBlock(blockchain, difficulty, bitCoin);
		// testing
		Block block1 = new Block(bitCoin.getHash());
		System.out.println("\nWalletA's balance is: " + walletA.getBalance(this));
		System.out.println("\nWalletA is Attempting to send funds (40) to WalletB...");
		block1.addTransaction(this, walletA.sendFunds(this, walletB.getPublicKey(), 40f), 0.1f);
		BlockUtils.addBlock(blockchain, difficulty, block1);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance(this));
		System.out.println("WalletB's balance is: " + walletB.getBalance(this));
		
		Block block2 = new Block(block1.getHash());
		System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
		block2.addTransaction(this, walletA.sendFunds(this, walletB.getPublicKey(), 1000f), 0.1f);
		BlockUtils.addBlock(blockchain, difficulty, block2);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance(this));
		System.out.println("WalletB's balance is: " + walletB.getBalance(this));
		
		Block block3 = new Block(block2.getHash());
		System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
		block3.addTransaction(this, walletB.sendFunds(this, walletA.getPublicKey(), 20), 0.1f);
		System.out.println("\nWalletA's balance is: " + walletA.getBalance(this));
		System.out.println("WalletB's balance is: " + walletB.getBalance(this));
	}
}
