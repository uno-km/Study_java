package Java.BlockChain;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BlockUtils
{
	
	/** @apiNote 난이도 어려움 */
	public static int DIFFICULTY_HARD = 6;
	/** @apiNote 난이도 보통 */
	public static int DIFFICULTY_NOMAL = 5;
	/** @apiNote 난이도 쉬움 */
	public static int DIFFICULTY_EASY = 2;
	/** @apiNote 난이도 */
	public static int DIFFICULTY = DIFFICULTY_NOMAL;
	/** @apiNote 최소 트랜잭션 값 */
	public static float MIN_TRANSACTION = 0.1f;
	/**
	 * @apiNote SHA256 알고리즘을 이용해서 해시값을 만들고 해당 값을 return한다.
	 * @param (String)
	 *        문자열
	 * @return (String) Hash Value
	 */
	public static String applySha256(String input)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++)
			{
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
				{
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			throw new RuntimeException("예외발생" + e.getMessage());
		}
	}
	
	/**
	 * @apiNote 체인 안에 속해있는 모든 블락들을 루프하며 해쉬값 비교. 해쉬 변수가 실제로 계산된 해쉬값과 같은지 그리고 이전
	 *          블락의 해쉬값이 이전 해쉬의 변수와 같은지를 확인.
	 * @param (List)
	 *        리스트형태의 블록 클래스
	 * @return (boolean)
	 */
	public static boolean inChainValid(List<Block> blockChains)
	{
		for (int i = 1; i < blockChains.size(); i++)
		{
			if (!blockChains.get(i).getHash().equals(blockChains.get(i).calculateHash()))
			{
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!blockChains.get(i - 1).getHash().equals(blockChains.get(i - 1).getPreviousHash()))
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	/**
	 * @apiNote ECDSA 서명을 적용하고 결과를 반환.
	 * @param (PrivateKey,
	 *        String) 암호키, 문자열
	 * @return (byte[]) byte arr
	 */
	public static byte[] applyECDSASig(PrivateKey privateKey, String input)
	{
		Signature dsa;
		byte[] output = new byte[0];
		try
		{
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return output;
	}
	
	/**
	 * @apiNote 서명확인
	 * @param (PublicKey,
	 *        String, byte[]) 공개키, 문자열, 서명들
	 * @return (boolean) 유효하면 true
	 */
	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature)
	{
		try
		{
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @apiNote 키를 받아 문자열로 리턴
	 * @param (Key)
	 *        키
	 * @return (String) 문자열
	 */
	public static String getStringFromKey(Key key)
	{
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	public static String getStringFromKey(Key key1, Key key2)
	{
		return Base64.getEncoder().encodeToString(key1.getEncoded()) + Base64.getEncoder().encodeToString(key2.getEncoded());
	}
	/**
	 * @apiNote 트랜잭션 배열을 처리하고 머클 루트를 반환
	 * @param (List
	 *        transactions) 트랜잭션 배열
	 * @return (String) 머클루트
	 */
	public static String getMerkleRoot(ArrayList<Transaction> transactions)
	{
		int count = transactions.size();
		ArrayList<String> previousTreeLayer = new ArrayList<String>();
		for (Transaction transaction : transactions)
		{
			previousTreeLayer.add(transaction.getTransactionId());
		}
		ArrayList<String> treeLayer = previousTreeLayer;
		while (count > 1)
		{
			treeLayer = new ArrayList<String>();
			for (int i = 1; i < previousTreeLayer.size(); i++)
			{
				treeLayer.add(applySha256(previousTreeLayer.get(i - 1) + previousTreeLayer.get(i)));
			}
			count = treeLayer.size();
			previousTreeLayer = treeLayer;
		}
		String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
		return merkleRoot;
	}
	/**
	 * @apiNote 채굴난이도 조절
	 * @param (int
	 *        ) 난이도 하드,노말,쉬움
	 * @return (String) 난이도를 0의 개수로 반환
	 */
	public static String getDificultyString(int difficulty)
	{
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	public static void addBlock(BlockVO blockVO, Block newBlock)
	{
		newBlock.mineBlock();
		blockVO.getBlockchain().add(newBlock);
	}
}
