package Cryptography.Lab2;

import java.math.BigInteger;

/**
 * Created by @kmartin62
 */
public class TriviumTest {

    private String[] fillLeadingZeros(String original, String cipher) {
        String[] originalArray = original.split(" ");
        String[] cipherArray = cipher.split(" ");

        for(int i = 0; i < originalArray.length; i ++) {
            StringBuilder s = new StringBuilder();
            int size = originalArray[i].length() - cipherArray[i].length();
            for(int j = 0; j < size; j ++) {
                s.append("0");
            }
            cipherArray[i] = s + cipherArray[i];
        }


        return cipherArray;

    }

    public static void main(String[] args) {

        TriviumTest tt = new TriviumTest();

        Converter converter = new Converter();
        String key = new BigInteger("0F62B5085BAE0154A7FA", 16).toString(2);
        String IV = new BigInteger("288FF65DC42B92F960C7", 16).toString(2);

        key = converter.manipulateKey(key);
        IV = converter.manipulateIV(IV);

        String str = "Kriptografija";
        String strBinary = converter.text_to_binary(str);
        System.out.println(str);

        String[] array = strBinary.split(" ");

        Trivium trivium = new Trivium(key, IV);

        String cipher = converter.binary_to_test(trivium.encrypt(array));
        String cipherBinary = converter.text_to_binary(cipher);
        System.out.println(cipher);

        String[] cipherArray = tt.fillLeadingZeros(strBinary, cipherBinary);

        String decrypted = converter.binary_to_test(trivium.decrypt(cipherArray));
        System.out.println(decrypted);

    }


}
