package Java.BlockChain;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class BlockVO
{
	private HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();
	private ArrayList<Block> blockchain = new ArrayList<Block>();
}
