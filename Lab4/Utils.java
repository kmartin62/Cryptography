package Cryptography.Lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by @kmartin62
 */
public class Utils {

    static List toBytes(String str) {
        List<String> list= new ArrayList<>();
        int index = 0;
        while (index<str.length()) {
            list.add(str.substring(index, Math.min(index+8,str.length())));
            index=index+8;
        }
        return list;
    }

    public static String padding(String str) {
        StringBuilder sb = new StringBuilder();
        if(str.length() == 64){
            return str;
        }
        else {
            for(int i = 0; i < 64 - str.length(); i ++) {
                sb.append("0");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    static Map splitToBlocks(String str){
        List lista = toBytes(str);
        Map<Integer, String> mapa = new HashMap();
        for(int i = 0; i < lista.size(); i ++) {
            String s = Long.toBinaryString(strToLng(lista.get(i).toString()));
            mapa.put(i, s);
        }
//        System.out.println(mapa);

        return mapa;
    }

     static Map toBlocks(String str) {
        Block block;
        List lista = toBytes(str);
//         System.out.println(lista);
         long d = strToLng((String) lista.get(0));
//         System.out.println(d);
        Map<Integer, Block> bitsMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
//         System.out.println(Long.toBinaryString(d));

        for(int i = 0; i < lista.size(); i ++) {
            byte[] bytes = lista.get(i).toString().getBytes();
            for(byte b : bytes) {
                int val = b;
                for (int j = 0; j < 8; j++)
                {
                    sb.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
//                sb.append(' ');
            }
            block = new Block(sb.toString());
            bitsMap.put(i, block);
            sb = new StringBuilder();
        }

        return bitsMap;
    }

    static String longToString(long l){

        StringBuilder sb = new StringBuilder();
        long mask = 255;

        for (int i=0;i<8;i++){
            long ll = l & mask;
            ll = ll>>(i*8);
            char c = (char) ll;
            sb.append(c);
            mask = mask<<8;
        }

        return sb.reverse().toString();
    }

    static long strToLng(String myString) {
        long toLong = 0;
        for (int i = 0; i < myString.length(); i++) {
            long c = (long) myString.charAt(i);
            int shift = (myString.length() - 1 - i) * 8;
            toLong += c << shift;
        }
        return toLong;
    }

}
