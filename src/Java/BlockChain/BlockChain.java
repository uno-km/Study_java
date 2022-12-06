package Java.BlockChain;

import java.util.Date;

class BlockChain
{
	public String hash;
	public String priviousHash;
	private String data;
	private long timeStamp;
	private int nonce;
	
	public BlockChain(String data, String previousHash)
	{
		this.data = data;
		this.priviousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public String calculateHash()
	{
		return StringUtils.applySha256(this.priviousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
	}
	
	public void mineBlock(int difficulty)
	{
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target))
		{
			nonce++;
			hash = calculateHash();
			System.out.println(hash);
			switch (nonce % 3)
			{
				case 0 :
					System.out.println("::: Mining .	");
					break;
				case 1 :
					System.out.println("::: Mining ..	");
					break;
				case 2 :
					System.out.println("::: Mining ...	");
					break;
			}
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}