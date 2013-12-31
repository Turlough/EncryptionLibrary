package encryption;


import utils.AesCore;
import utils.HexEncoding;

public class AesEncryptor implements IEncryptor {

    AesCore core;

    public AesEncryptor(String passphrase) {
        core = new AesCore(passphrase);
    }

    @Override
    public String encrypt(String input) {
        if(input == null || input.equals(""))
            return "";

        try {
            byte[] bytes = core.encrypt(input);
            return HexEncoding.getHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";//should not happen
        }
    }

    @Override
    public String decrypt(String hex) {
        if(hex == null || hex.equals(""))
            return "";

        byte[] bytes = HexEncoding.getBytes(hex);
        try {
            return core.decrypt(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";//should not happen
        }
    }
}
