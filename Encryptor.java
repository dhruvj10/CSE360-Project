package Encryptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;


// 
//Made By Logan Goswick

public class Encryptor {

	public static void main(String[] args) 
	{
        try {
			System.out.print(new String(Files.readAllBytes(Paths.get("Test.txt"))));
			EncryptOrDecrypt("Test.txt");
			System.out.print(new String(Files.readAllBytes(Paths.get("Test.txt"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void EncryptOrDecrypt(String fileName) 
	{
	    
	    byte[] key = {0x42, 0x75, 0x67, 0x73, 0x61, 0x72, 0x65, 0x69, 0x6E, 0x79, 0x6F, 0x75, 0x6E, 0x6F, 0x77, 0x21};
	    ;
		try 
		{
			byte[] content = Files.readAllBytes(Paths.get(fileName));
		    byte[] encryptedBytes = new byte[content.length]; 
		    for (int i = 0; i < content.length; i++)
		    {
		    	
		    	encryptedBytes[i] = (byte)(content[i] ^ key[i % 16]);
		    }
			Files.write(Paths.get(fileName), encryptedBytes);
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	  }
		
		

}
