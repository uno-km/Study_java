package Java.File.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChagneIp
{
	public static void main(String[] args) throws Exception
	{
		// set the IP address, subnet mask, and default gateway for the
		// "Ethernet" adapter
		ProcessBuilder pb = new ProcessBuilder("netsh", "wlan", "show profiles" );
		Process process = pb.start();
		
		// print the output of the command
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}
	}
}