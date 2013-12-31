package utils;

public class HexEncoding {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String getHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

//    public static String getHex(String input){
//        char[] chars = input.toCharArray();
//
//        byte[] bytes = new byte[chars.length];
//        for (int i = 0; i < chars.length; i++) {
//            bytes[i]=(byte)chars[i];
//        }
//
//        return getHex(bytes);
//    }

    public static byte[] getBytes(String hex) {


        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }


}
