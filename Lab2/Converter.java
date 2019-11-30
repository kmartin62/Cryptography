package Cryptography.Lab2;


/**
 * Created by @kmartin62
 */
class Converter {

    String text_to_binary(String str) {
        StringBuilder result = new StringBuilder();
        char[] messChar = str.toCharArray();

        for (char c : messChar) {
            result.append(Integer.toBinaryString(c)).append(" ");
        }

        return result.toString();
    }

    String binary_to_test(String[] array){

        String str1;
        StringBuilder sb = new StringBuilder();

        for (String s : array) {
            int charCode = Integer.parseInt(s, 2);
            str1 = Character.toString((char) charCode);
            sb.append(str1);
        }
        return sb.toString();
    }

    String manipulateKey(String key) {

        if(key.length() < 80){
            StringBuilder pom = new StringBuilder();
            for(int i = key.length(); i < 80; i ++){
                pom.append("0");
            }
            return key + pom;
        }
        else {
            return key;
        }
    }

    String manipulateIV(String IV) {

        if(IV.length() < 80){
            StringBuilder pom = new StringBuilder();
            for(int i = IV.length(); i < 80; i ++){
                pom.append("0");
            }
            return IV + pom;
        }
        else {
            return IV;
        }
    }
}