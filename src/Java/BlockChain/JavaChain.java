package Java.BlockChain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.GsonBuilder;

public class JavaChain
{
	static int DIFFICULTY = 3;
	public static void main(String[] args) throws JsonProcessingException
	{
		ArrayList<BlockChain> blockChains = new ArrayList<>();
		
		BlockChain unoBlock = new BlockChain("My First Block Chain", "0");
		blockChains.add(unoBlock);
		unoBlock.mineBlock(DIFFICULTY);
		
		BlockChain dosBlock = new BlockChain("My Second Block Chain", unoBlock.hash);
		blockChains.add(dosBlock);
		dosBlock.mineBlock(DIFFICULTY);
		
		BlockChain tresBlock = new BlockChain("My Third Block Chain", dosBlock.hash);
		blockChains.add(tresBlock);
		tresBlock.mineBlock(DIFFICULTY);
		
		System.out.println("Bloack is Valid : " + inChainValid(blockChains));
		String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(blockChains);
		System.out.println(jsonStr);
	}
	
	private static boolean inChainValid(List<BlockChain> blockChains)
	{
		for (int i = 1; i < blockChains.size(); i++)
		{
			if (!blockChains.get(i).hash.equals(blockChains.get(i).calculateHash()))
			{
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!blockChains.get(i - 1).hash.equals(blockChains.get(i - 1).priviousHash))
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
}
