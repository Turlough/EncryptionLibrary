EncryptionLibrary
=================

Simple Aes String encryption and decryption.
Contains IEncryptor, NullEncryptor and AesEncryptor, as well as utils and unit tests.

IEncryptor provides this interface:

public interface IEncryptor {
    String encrypt(String input);
    String decrypt (String input);
}
