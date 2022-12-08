package Java.BlockChain;

import java.util.ArrayList;
import java.util.Date;

class Block
{
	private String hash; // 해시값
	private String previousHash; // 이전 해시값
	private long timeStamp;
	private int nonce;
	private String merkleRoot;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Block(String previousHash)
	{
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash()
	{
		return BlockUtils.applySha256(this.previousHash + Long.toString(this.timeStamp) + Integer.toString(this.nonce) + this.merkleRoot);
	}
	
	public void mineBlock(int difficulty)
	{
		this.merkleRoot = BlockUtils.getMerkleRoot(this.transactions);
		String tmpHash = this.hash;
		while (!tmpHash.substring(0, difficulty).equals(BlockUtils.getDificultyString(difficulty)))
		{
			this.nonce++;
			tmpHash = this.calculateHash();
		}
		this.hash = tmpHash;
		System.out.println("Block Mined!!! : " + this.hash);
	}
	// 블록에 트랜잭션 추가
	public boolean addTransaction(UnoChain uno, Transaction transaction, float minimumTransaction)
	{
		// 거래를 처리하고 유효한지 확인하고, 블록이 생성 블록이 아니라면 무시
		if (transaction == null) return false;
		if ((this.previousHash != "0"))
		{
			if ((transaction.processTransaction(uno, minimumTransaction) != true))
			{
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
		}
		this.transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
	public String getHash()
	{
		return this.hash;
	}
	
	public String getPreviousHash()
	{
		return this.previousHash;
	}
}