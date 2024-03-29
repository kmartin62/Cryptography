package Cryptography.Lab4;

import Cryptography.Lab3.DES;

import java.util.HashMap;
import java.util.Map;

import static Cryptography.Lab4.Utils.*;

/**
 * Created by @kmartin62
 */
public class BlockCBC {
    private Map blocks;
    private HashMap<Integer, Long> cipherMap;
    private DES des;
    private long key;
    private long IV;


    BlockCBC(Map blocks, long key, long IV) {
        this.blocks = new HashMap();
        cipherMap = new HashMap<>();
        this.blocks = blocks;
        this.key = key;
        this.IV = IV;
        des = new DES();

    }


    String encryptCBC() {

        StringBuilder sb = new StringBuilder();
        long roundIV = IV;
        for(int i = 0; i < blocks.size(); i ++) {
            String s = blocks.get(i).toString();
            long xor = Long.parseLong(s, 2) ^ roundIV;
            long cipher = des.encrypt(xor, key);
            cipherMap.put(i, cipher);
            sb.append(longToString(cipher));
            roundIV = cipher;
        }

        return sb.toString();
    }

    String decryptCBC(){
        StringBuilder sb = new StringBuilder();
        long roundIV = IV;

        for(Map.Entry<Integer, Long> m : cipherMap.entrySet()) {
            long c = cipherMap.get(m.getKey());
            long decipher = des.decrypt(c, key);
            long text = decipher ^ roundIV;
            sb.append(longToString(text));
            roundIV = c;
        }


        return sb.toString();
    }

    public void deleteBlock(int block_number) {
        cipherMap.remove(block_number);
    }

    public void replaceBlocks(int block_1, int block_2) {
        long block1 = cipherMap.get(block_1);
        long block2 = cipherMap.get(block_2);

        cipherMap.put(block_1, block2);
        cipherMap.put(block_2, block1);
    }


}
