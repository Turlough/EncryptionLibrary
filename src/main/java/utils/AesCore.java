package utils;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class AesCore {
    Key key;
    Cipher aes;

    public AesCore(String passphrase)
            throws InvalidParameterException{
        if(passphrase == null || passphrase.equals(""))
            throw new InvalidParameterException("Passphrase cannot be null or empty");

        try {
            aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            key = getKey(passphrase);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Key getKey(String passphrase)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(passphrase.getBytes());
        SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        return key;
    }

    public byte[] encrypt(String cleartext)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        aes.init(Cipher.ENCRYPT_MODE,  key);
        return aes.doFinal(cleartext.getBytes());
    }

    public String decrypt(byte[] ciphertext)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        aes.init(Cipher.DECRYPT_MODE, key);
        return new String(aes.doFinal(ciphertext));
    }

}
