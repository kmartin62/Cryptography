package Cryptography.Lab2;


import java.util.stream.IntStream;

/**
 * Created by @kmartin62
 */
public class Trivium {

    private int[] initial_State = new int[288];
//    private int[] key_Array = new int[80];
//    private int[] IV_Array = new int[80];
    private int[] keystream;

    private String key;
    private String IV;

    Trivium(String key, String IV){
        this.key = key;
        this.IV = IV;
        setup();
    }

    private void setup(){
        String[] keyArray = key.split("");
        String[] vectorArray = IV.split("");

        IntStream.range(0, keyArray.length).forEach(i -> initial_State[i] = Integer.parseInt(keyArray[i]));
        IntStream.range(0, vectorArray.length).forEach(i -> initial_State[i+keyArray.length] = Integer.parseInt(vectorArray[i]));

        initial_State[285] = 1;
        initial_State[286] = 1;
        initial_State[287] = 1;

//        IntStream.range(0, initial_State.length).forEach(i -> System.out.print(initial_State[i]));

        for(int i = 0; i < 4*288; i ++) {

            int t1 = initial_State[65] ^ initial_State[90] & initial_State[91] ^ initial_State[92] ^ initial_State[170];
            int t2 = initial_State[161] ^ initial_State[174] & initial_State[175] ^ initial_State[176] ^ initial_State[263];
            int t3 = initial_State[242] ^ initial_State[285] & initial_State[286] ^ initial_State[287] ^ initial_State[68];

            rotateArray(initial_State);

            initial_State[0] = t3;
            initial_State[93] = t1;
            initial_State[177] = t2;
        }

    }

    private int generate_Keystream(){
        int t1 = initial_State[65] ^ initial_State[92];
        int t2 = initial_State[161] ^ initial_State[176];
        int t3 = initial_State[242] ^ initial_State[287];

        int z = t1 ^ t2 ^ t3;

         t1 = initial_State[65] ^ initial_State[90] & initial_State[91] ^ initial_State[92] ^ initial_State[170];
         t2 = initial_State[161] ^ initial_State[174] & initial_State[175] ^ initial_State[176] ^ initial_State[263];
         t3 = initial_State[242] ^ initial_State[285] & initial_State[286] ^ initial_State[287] ^ initial_State[68];

        rotateArray(initial_State);

        initial_State[0] = t3;
        initial_State[93] = t1;
        initial_State[177] = t2;


        return z;
    }

    private void keyStream(int message_length) {

        keystream = new int[message_length];

        IntStream.range(0, message_length).forEach(i -> keystream[i] = generate_Keystream());

    }

    String[] encrypt(String[] originalList) {
        StringBuilder sb = new StringBuilder();
        int k = 0;
        String[] s = convertArrayToStringMethod(originalList).split("");
        keyStream(convertArrayToStringMethod(originalList).length());
        int[] encryptedMessage = new int[keystream.length];
        IntStream.range(0, keystream.length).forEach(i -> encryptedMessage[i] = Integer.parseInt(s[i]) ^ keystream[i]);


        for (String s1 : originalList) {
            for (int j = k; j < s1.length() + k; j++) {
                sb.append(encryptedMessage[j]);
            }
            sb.append(" ");
            k += s1.length();
        }

        return sb.toString().split(" ");
    }

    String[] decrypt(String[] encryptedList) {

        int k = 0;
        StringBuilder sb = new StringBuilder();
        String[] s = convertArrayToStringMethod(encryptedList).split("");
        int[] decryptMessage = new int[keystream.length];

        IntStream.range(0, keystream.length).forEach(i -> decryptMessage[i] = Integer.parseInt(s[i]) ^ keystream[i]);


        for (String s1 : encryptedList) {
            for (int j = k; j < s1.length() + k; j++) {
                sb.append(decryptMessage[j]);
            }
            sb.append(" ");
            k += s1.length();
        }

        return sb.toString().split(" ");
    }



    private static String convertArrayToStringMethod(String[] strArray) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String s : strArray) {

            stringBuilder.append(s);

        }

        return stringBuilder.toString();

    }

    private void rotateArray(int[] arr){
            int j, last;
            last = arr[arr.length-1];

            for(j = arr.length-1; j > 0; j--){
                arr[j] = arr[j-1];
            }
            arr[0] = last;
    }

    @Override
    public String toString() {
        return key + "\n" + IV;
    }


    }

