package Cryptography.Lab3;

/**
 * Class to provide a simple user interface to the DES algorithm.
 */
public class DESInterface
{

    private static String padding(String s) {
        if(s.length() == 64) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 64 - s.length(); i ++) {
            sb.append("0");
        }

        sb.append(s);
        return sb.toString();
    }
    public static void main(String[] args)
    {

        DES des = new DES();

        long m = 0;
        long k = Long.MAX_VALUE;

		String bits = Long.toBinaryString(des.encrypt(m,k));
        System.out.println(padding(bits));

//        m = 2;
//        System.out.println(padding(Long.toBinaryString(des.encrypt(m, k))));

    }


//    /**
//     * Runs DES in CBC mode on input @param blocks using @param key with appropriate output
//     * displayed.
//     */
//    private static void runCBC(long[] blocks, long key, long IV)
//    {
//        DES des = new DES();
//        byte[] bytes;
//        long[] cipherTexts, plainTexts;
//
//        cipherTexts = des.CBCEncrypt(blocks, key, IV);
//
//        System.out.println("\nEncrypted ciphertext: ");
//        for (long block : cipherTexts)
//        {
//            bytes = ByteBuffer.allocate(8).putLong(block).array();
//            System.out.print(new String(bytes));
//        }
//
//        plainTexts = des.CBCDecrypt(cipherTexts, key, IV);
//        System.out.println("\nDecrypted plaintext: ");
//        for (long block : plainTexts)
//        {
//            bytes = ByteBuffer.allocate(8).putLong(block).array();
//            System.out.print(new String(bytes));
//        }
//    }
//
//    /**
//     * Runs standard DES mode encryption and decryption on @param blocks using @param key with
//     * appropriate output displayed.
//     */
//    private static void runCipher(long[] blocks, long key)
//    {
//        DES des = new DES();
//        byte[] bytes = new byte[0];
//        long[] cipherTexts = new long[blocks.length], plainTexts = new long[blocks.length];
//
////        System.out.println("Input plaintext: ");
//        for (long block : blocks)
//        {
//            bytes = ByteBuffer.allocate(8).putLong(block).array();
//////            System.out.print(new String(bytes));
//        }
//
//        System.out.println("\nEncrypted ciphertext: ");
//        for (int i = 0; i < blocks.length; i++)
//        {
//            cipherTexts[i] = des.encrypt(blocks[i], key);
//        }
//
//        for (long block : cipherTexts)
//        {
//            bytes = ByteBuffer.allocate(8).putLong(block).array();
//            System.out.print(new String(bytes));
//        }
//
//        long value = 0;
//        for (int i = 0; i < bytes.length; i++)
//        {
//            value = (value << 8) + (bytes[i] & 0xff);
//        }
//
//        System.out.println("\n" + new BigInteger(bytes).longValue());
//        System.out.println(Long.toBinaryString(value));
//
//        System.out.println("\nDecrypted plaintext: ");
//        for (int i = 0; i < cipherTexts.length; i++)
//        {
//            plainTexts[i] = des.decrypt(cipherTexts[i], key);
//        }
//        for (long block : plainTexts)
//        {
//            bytes = ByteBuffer.allocate(8).putLong(block).array();
//            System.out.print(new String(bytes));
//        }
//
//        value = 0;
//        for (int i = 0; i < bytes.length; i++)
//        {
//            value = (value << 8) + (bytes[i] & 0xff);
//        }
//
//        System.out.println(new BigInteger(bytes).longValue());
//        System.out.println(Long.toBinaryString(value));
//    }
//
//    /**
//     * Splits the input bytes into blocks of 64 bits.
//     *
//     * @param input The input text as a byte array.
//     * @return An array of longs, representing the 64 bit blocks.
//     */
//    private static long[] splitInputIntoBlocks(byte[] input)
//    {
//        long blocks[] = new long[input.length / 8 + 1];
//
//        for (int i = 0, j = -1; i < input.length; i++)
//        {
//            if (i % 8 == 0)
//                j++;
//            blocks[j] <<= 8;
//            blocks[j] |= input[i];
//        }
//
//        return blocks;
//    }
//
//    /**
//     * Gets a file path to the input file from @param reader.
//     *
//     * @return The contents of the file in byte array form.
//     */
//    private static byte[] getText()
//    {
//        String path = "";
////        try
////        {
//            path = "/home/kmartin62/Desktop/test.txt";
////        } catch (IOException e)
////        {
////            printErrorAndDie("");
////        }
//
//        return getByteArrayFromFile(path);
//    }
//
//    /**
//     * @param filePath Path to file containing input text.
//     * @return Byte array representing the text in the file.
//     */
//    private static byte[] getByteArrayFromFile(String filePath)
//    {
//        File file = new File(filePath);
//        byte[] fileBuff = new byte[(int) file.length()];
//
//        try
//        {
//            DataInputStream fileStream = new DataInputStream(new FileInputStream(file));
//            fileStream.readFully(fileBuff);
//            fileStream.close();
//        } catch (IOException e)
//        {
//            printErrorAndDie("Cannot read from file.");
//        }
//
//        return fileBuff;
//    }
//
//    /**
//     * Gets a key from @param reader and formats it correctly.
//     *
//     * @return A 64 bit key as type long. If the input is greater than 64 bits, it will be
//     *         truncated. If less than 64 bits, it will be left-padded with 0s.
//     */
//    private static long getKey(BufferedReader reader)
//    {
//        String keyStr = "";
//        byte[] keyBytes;
//        long key64 = 0;
//
//        try
//        {
//            keyStr = reader.readLine();
//        } catch (IOException e)
//        {
//            printErrorAndDie("");
//        }
//
//        if (keyStr.length() > 8)
//        {
//            System.out.println("Input is greater than 64 bits.");
//            System.exit(0);
//        }
//
//        keyBytes = keyStr.getBytes();
//
//        for (byte keyByte : keyBytes)
//        {
//            key64 <<= 8;
//            key64 |= keyByte;
//        }
//
//        return key64;
//    }
//
//    /**
//     * Uses @param reader to get user confirmation on CBC mode.
//     *
//     * @return True if user wants CBC mode, else false.
//     */
//    private static boolean getCBCConfirmation(BufferedReader reader)
//    {
//        int c = 0;
//        try
//        {
//            c = reader.read();
//        } catch (IOException e)
//        {
//            printErrorAndDie("");
//        }
//
//        return (Character.toLowerCase(c) == 'y');
//    }
//
//    private static void printErrorAndDie(String message)
//    {
//        System.err.println("Fatal IO error encountered." + "\n" + message);
//        System.exit(1);
//    }
}