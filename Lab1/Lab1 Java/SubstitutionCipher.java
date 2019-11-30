package Cryptography.Lab1;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by @kmartin62
 */
public class SubstitutionCipher {

    private Map frequency(String filename) throws IOException {
        Map<Character, Integer> frequency = new HashMap<>();
        float counter = 0;

        BufferedReader bf = null;

        {
            try {
                bf = new BufferedReader(new FileReader(new File(filename)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        int c;

        while ((c = bf.read()) != -1) {
            counter++;
            char character = (char) c;

            if(frequency.containsKey(character)){
                frequency.put(character, frequency.get(character) + 1);
            }
            else {
                frequency.put(character, 1);
            }

        }


        Map<Character, Float> percent = new HashMap<>();
        for(Character key : frequency.keySet()){
            percent.put(key, ((frequency.get(key)/counter)*100));
        }


        return percent
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                ); //Sort in a descending order


//        return frequency
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
//                ); //Sort in a descending order

    }

    private Map bigrame(String filename) throws IOException {
        BufferedReader bf = null;
        Map<String, Integer> bigrameMap = new HashMap<>();

        {
            try {
                bf = new BufferedReader(new FileReader(new File(filename)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String[] array = bf.readLine().split("");
        for(int i = 0; i < 2; i ++){
            for(int j = i; j < array.length - 1; j ++) {
                if(!bigrameMap.containsKey(array[j]+array[j+1])){
                    bigrameMap.put(array[j]+array[j+1], 1);
                }
                else {
                    bigrameMap.put(array[j]+array[j+1], bigrameMap.get(array[j]+array[j+1])+1);
                }
            }
        }

        float counter = 0;

        for(int value : bigrameMap.values()){
            counter += value;
        }

        Map<String , Float> percent = new HashMap<>();
        for(String key : bigrameMap.keySet()){
            percent.put(key, ((bigrameMap.get(key)/counter)*100));
        }


        return percent
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                ); //Sort in a descending order
    }

    private void replace(String filename) throws IOException {
        HashMap mapa = (HashMap) frequency(filename);
        Map<Character, Character> decypherMap = new HashMap();
//        char[] array = {'A', 'O', 'И', 'E', 'Т', 'Н', 'Р', 'С', 'К', 'В',
//                'Д', 'П', 'М', 'Л', 'У', 'Ј', 'З', 'Г', 'Б', 'Ц', 'Ш', 'Ж',
//                'Ф', 'Њ', 'Ќ', 'Х', 'Ѓ', 'Ѕ', 'Љ', 'Џ'};

        char[] array = {'И', 'А', 'Т', 'О', 'Н', 'Е', 'К', 'С', 'Р', 'В',
                'Д', 'У', 'М', 'П', 'Л', 'Ј', 'Ф', 'З', 'Ч', 'Г', 'Б', 'Ж',
                'Ц', 'Х', 'Њ', 'Ш', 'Ѓ', 'Ѕ', 'Љ', 'Џ'};

        int i = 0;

        for(Object c : mapa.keySet()){
            decypherMap.put((Character) c, array[i]);
            i++;
        }

        PrintWriter writer = new PrintWriter("/home/kmartin62/Desktop/the-file-name.txt", "UTF-8");

        BufferedReader bf = null;

        {
            try {
                bf = new BufferedReader(new FileReader(new File(filename)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        int c;

        while ((c = bf.read()) != -1) {
            char character = (char) c;
            writer.print(decypherMap.get(character));
        }

//        writer.print("The first line");
//        writer.print("Втора линија");
        writer.close();

        System.out.println(decypherMap);
    }


    public static void main(String[] args) throws IOException {

        final String PATH = "/home/kmartin62/Desktop/encrypted_text.txt";

        SubstitutionCipher substitutionCipher = new SubstitutionCipher();
        System.out.println(substitutionCipher.frequency(PATH));
        System.out.println(substitutionCipher.bigrame(PATH));
        substitutionCipher.replace(PATH);
    }

}
