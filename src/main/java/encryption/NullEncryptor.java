package encryption;


public class NullEncryptor implements IEncryptor {
    @Override
    public String encrypt(String input) {
        return input;
    }

    @Override
    public String decrypt(String input) {
        return input;
    }
}
