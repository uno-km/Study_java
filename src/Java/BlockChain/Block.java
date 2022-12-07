package Java.BlockChain;

import java.util.Date;

class Block
{
	private String hash; // 해시값
	private String priviousHash; // 이전 해시값
	private String data; // 데이터
	private long timeStamp;
	private int nonce;
	
	public Block(String data, String previousHash)
	{
		this.data = data;
		this.priviousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash()
	{
		return BlockUtils.applySha256(this.priviousHash + Long.toString(this.timeStamp) + Integer.toString(this.nonce) + this.data);
	}
	
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		String tmpHash = this.hash;
		while (!tmpHash.substring(0, difficulty).equals(target))
		{
			this.nonce++;
			tmpHash = this.calculateHash();
			// System.out.println(hash);
			// System.out.println("메모리사용량 : " +
			// Math.round(MemoryUtils.getSystemCpuLoad() * 100));
		}
		this.hash = tmpHash;
		System.out.println("Block Mined!!! : " + this.hash);
	}
	
	public String getHash()
	{
		return this.hash;
	}
	
	public String getPriviousHash()
	{
		return this.priviousHash;
	}
	
	public String getData()
	{
		return this.data;
	}
}