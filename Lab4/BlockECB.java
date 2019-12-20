package Cryptography.Lab4;

import Cryptography.Lab3.DES;

import java.util.HashMap;
import java.util.Map;

import static Cryptography.Lab4.Utils.longToString;

/**
 * Created by @kmartin62
 */
public class BlockECB {
    private Map blocks;
    private Map<Integer, Long> cipherMap;
    private DES des;
    private long key;

    BlockECB(Map mapa, long key) {
        this.blocks = new HashMap();
        this.blocks = mapa;
        this.key = key;
        des = new DES();
        cipherMap = new HashMap<>();
    }


    String encryptECB() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < blocks.size(); i ++) {
            String s = blocks.get(i).toString();
            long cipher = des.encrypt(Long.parseLong(s, 2), key);
            cipherMap.put(i, cipher);
            sb.append(longToString(cipher));
        }
        return sb.toString();
    }

    String decryptECB(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Long> m : cipherMap.entrySet()) {
            sb.append(longToString(des.decrypt(cipherMap.get(m.getKey()), key)));
        }

        return sb.toString();

    }

    void replaceBlocks(int block_1, int block_2) {
        long block1 = cipherMap.get(block_1);
        long block2 = cipherMap.get(block_2);

        cipherMap.put(block_1, block2);
        cipherMap.put(block_2, block1);
    }

    public void deleteBlock(int block_number) {
        cipherMap.remove(block_number);
    }

}
