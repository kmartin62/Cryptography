package Cryptography.Lab4;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Cryptography.Lab4.Utils.*;

/**
 * Created by @kmartin62
 */
public class Main {

    public static void main(String[] args) {

        final long key = 591826312;
        final long IV = Long.parseLong("00003442A91077DE45AC", 16);

        String str1 = "Knigata po Kriptografija vredi 10 dolari";

        System.out.println("ECB MODE:");
        Map blocks = splitToBlocks(str1);
//        System.out.println(mapa);
        BlockECB blockECB = new BlockECB(blocks, key);
        String m = blockECB.encryptECB();
//        blockECB.deleteBlock(4);
        System.out.println(m);
//        System.out.println(mapa1);
        System.out.println("-------------------------------------------------------------------------");
        String decipher = blockECB.decryptECB();
        System.out.println(decipher);

        System.out.println("\n========================================================================");
        System.out.println("CBC MODE:");
        BlockCBC blockCBC = new BlockCBC(blocks, key, IV);
        String mcbc = blockCBC.encryptCBC();
        System.out.println(mcbc);
        System.out.println("-------------------------------------------------------------------------");
        String decipherCBC = blockCBC.decryptCBC();
        System.out.println(decipherCBC);

    }
}
