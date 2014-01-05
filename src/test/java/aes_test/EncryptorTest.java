package aes_test;

import encryption.AesEncryptor;
import utils.AesCore;

import org.junit.Before;
import org.junit.Test;


import java.security.InvalidParameterException;

import static org.junit.Assert.*;


public class EncryptorTest {
    AesCore core;
    AesEncryptor encryptor;
    @Before
    public void setUp() throws Exception {
        core = new AesCore("boolaboola");
        encryptor = new AesEncryptor("smog filled villages stinking of little villagers");
    }

    @Test
    public void testGetKey_CreatesSixteenByteKey() throws Exception {
        byte[] b = core.getKey("boolaboola").getEncoded();
        assertEquals(16, b.length);
    }

    @Test(expected = InvalidParameterException.class )
    public void testAesEncryptorConstructor_ThrowsException_GivenNull(){
        encryptor = new AesEncryptor(null);
    }

    @Test(expected = InvalidParameterException.class )
    public void testAesEncryptorConstructor_ThrowsException_GivenEmpty(){
        encryptor = new AesEncryptor("");
    }

    @Test
    public void testAesCore_EncryptFollowedByDecrypt_UsingBytes() throws Exception {
        String expected = "Bullets for Jesus, Rockets for Muhammad";
        byte[] b = core.encrypt(expected);
        String actual = core.decrypt(b);

        assertEquals(expected, actual);

    }

    @Test
    public void testAesEncryptor_EncryptFollowedByDecrypt_UsingStrings() throws Exception {
        String expected = "Bullets for Jesus,\n Rockets for Muhammad";
        String encrypted = encryptor.encrypt(expected);
        String actual = encryptor.decrypt(encrypted);

        System.out.println("Original: " + expected);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testAesEncryptor_returnsEmpty_GivenEmptyOrNullInput(){
        assertEquals("",encryptor.encrypt(""));
        assertEquals("",encryptor.decrypt(""));
        assertEquals("",encryptor.encrypt(null));
        assertEquals("",encryptor.decrypt(null));
    }

}
