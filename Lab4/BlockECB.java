package Cryptography.Lab4;

import Cryptography.Lab3.DES;

import java.util.HashMap;
import java.util.Map;

import static Cryptography.Lab4.Utils.longToString;
import static Cryptography.Lab4.Utils.strToLng;

/**
 * Created by @kmartin62
 */
public class BlockECB {
    private Map blocks;
    private HashMap decipherMap = new HashMap();
    private HashMap<Integer, Long> cipherMap = new HashMap<>();
    private DES des;
    private long key;

    public BlockECB(Map mapa, long key) {
        this.blocks = new HashMap();
        this.blocks = mapa;
        this.key = key;
        des = new DES();
    }


    public String encryptECB() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < blocks.size(); i ++) {
            String s = blocks.get(i).toString();
            long cipher = des.encrypt(Long.parseLong(s, 2), key);
            cipherMap.put(i, cipher);
            decipherMap.put(i, des.decrypt(cipher, key));
            sb.append(longToString(cipher));
        }
        return sb.toString();
    }

    public String decryptECB(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < decipherMap.size(); i ++) {
            sb.append(longToString(des.decrypt(cipherMap.get(i), key)));
//            System.out.println(longToString(des.decrypt(cipherMap.get(i), key)));
        }

        return sb.toString();

    }

    public void deleteCipherBlock(int block_number){
        decipherMap.replace(block_number, null);
    }

    public void deleteBlock(int block_number) {
        cipherMap.replace(block_number, null);
    }

    public void insertBlock(int block_number, String str) {
        if(cipherMap.containsKey(block_number)){
            long d = strToLng(str);
            cipherMap.replace(block_number, d);
        }
        else {
            System.out.println("Nevaliden vlez");
        }
    }
}
