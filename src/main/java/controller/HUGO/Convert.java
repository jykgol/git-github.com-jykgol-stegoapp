
package controller.HUGO;

public class Convert {

    private static final int MAX_INT_LEN = 4; // длинна заголовка сообщения в котором будет записана длинна стего

    public byte[] buildStego(byte[] msgBytes) {

        // System.out.println("Байты текстового сообщения: " + msgBytes);

        byte[] lenBs = intToBytes(msgBytes.length);

        int totalLen = lenBs.length + msgBytes.length;
        byte[] stego = new byte[totalLen];

        System.arraycopy(lenBs, 0, stego, 0, lenBs.length);
        System.arraycopy(msgBytes, 0, stego, lenBs.length, msgBytes.length);

        // System.out.println("Байты стего: " + stego);
        return stego;

    } // end of buildStego()

    public static byte[] intToBytes(int i) {
        // map the parts of the integer to a byte array
        byte[] integerBs = new byte[MAX_INT_LEN];
        integerBs[0] = (byte) ((i >>> 24) & 0xFF);
        integerBs[1] = (byte) ((i >>> 16) & 0xFF);
        integerBs[2] = (byte) ((i >>> 8) & 0xFF);
        integerBs[3] = (byte) (i & 0xFF);
        return integerBs;
    } // end of intToBytes()

}
