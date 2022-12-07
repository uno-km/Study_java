package Java.BlockChain;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.GsonBuilder;

import Java.Reflect.MemoryChecker;

public class BlockMining
{
	public static void main(String[] args) throws JsonProcessingException
	{
//		Thread checker = new MemoryChecker();
//		checker.start();
		ArrayList<Block> blockChains = new ArrayList<>();
		for (int i = 0; i < 4; i++)
		{
			Block block;
			if (i == 0)
			{
				block = new Block("나의 " + i + "번째 블록", "0");
			}
			else
			{
				block = new Block("나의 " + i + "번째 블록", blockChains.get(i - 1).getHash());
			}
			block.mineBlock(BlockUtils.DIFFICULTY_NOMAL);
			blockChains.add(block);
		}
		System.out.println("Bloack is Valid : " + BlockUtils.inChainValid(blockChains));
		String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(blockChains);
		System.out.println(jsonStr);
	}
}
