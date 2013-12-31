package utils;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class AesCore {
    Key key;
    Cipher cipher;

    public AesCore(String passphrase)
            throws InvalidParameterException{
        if(passphrase == null || passphrase.equals(""))
            throw new InvalidParameterException("Passphrase cannot be null or empty");

        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
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
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(cleartext.getBytes());
    }

    public String decrypt(byte[] ciphertext)
            throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(ciphertext));
    }

}
