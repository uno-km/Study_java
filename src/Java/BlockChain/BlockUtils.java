package Java.BlockChain;

import java.security.MessageDigest;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils
{
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
	
	public static <T> String getJSONArrayFromArrayList(List<T> list)
	{
		ObjectMapper objMapper = new ObjectMapper();
		String outStr = "";
		try
		{
			for (T t : list)
			{
				outStr += objMapper.writeValueAsString(t);
			}
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return outStr;
	}
}
