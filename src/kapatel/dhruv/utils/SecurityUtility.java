package kapatel.dhruv.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * 
 * Derived from http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 * should use bcrypt or scrypt 
 * 
 * @author dhruv
 *
 */

public class SecurityUtility {
	
	/**
	 * This method generate 32 characters password hash for given password and salt using MD5. 
	 * 
	 * @param passwordToHash
	 * @param salt
	 * @return password hash 
	 */
	
	
	public static String getSecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
  /**
   * This method generate 32 characters salt value using PRNG algorithm supplied by the SUN provider
   * 
   * @return
   */
	
    public static String getSalt() 
    {
        //Always use a SecureRandom generator
        SecureRandom sr = null;
		try 
		{
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		} 
		catch (NoSuchAlgorithmException | NoSuchProviderException e) 
		{
			e.printStackTrace();
		}
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        
        //salt[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< salt.length ;i++)
        {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        //return salt
        return sb.toString();
    }
    
    /**
     * This method compares database password hash with hash value of entered password   
     * 
     * @param passwordToHash
     * @param salt
     * @param dbPassword
     * @return true if values matched else false
     */
    
    public static  boolean validatePassword(String passwordToHash, String salt,String dbPassword)
    {
    	// Create MessageDigest instance for MD5
        MessageDigest md = null;
		try 
		{
			md = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
        //Add password bytes to digest
        md.update(salt.getBytes());
        //Get the hash's bytes 
        byte[] bytes = md.digest(passwordToHash.getBytes());
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        String generatedPassword = sb.toString();
        
//        System.out.println("generated hash"+generatedPassword);
//        System.out.println("db hash"+dbPassword);
        
        if(generatedPassword.equals(dbPassword))
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }
}
