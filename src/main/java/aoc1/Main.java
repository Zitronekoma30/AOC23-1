package main.java.aoc1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;

class Main {
    public static Map<String, String> nums = new HashMap<>();
    static {
        nums.put("one", "1");
        nums.put("two", "2");
        nums.put("three", "3");
        nums.put("four", "4");
        nums.put("five", "5");
        nums.put("six", "6");
        nums.put("seven", "7");
        nums.put("eight", "8");
        nums.put("nine", "9");
    }
    
    public static boolean doTask2 = true;

    public static void main(String[] args) {
        String input = getFileContent("input.txt");
        String[] lines = input.split(System.lineSeparator());
        System.out.println(getSum(lines, doTask2));
    }

    public static String getFileContent(String path){
        String filePath = path;
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public static int getSum(String[] in, boolean numWords){
        int sum = 0;
        for (String str : in){
            if (numWords){
                str = cleanNums(str);
            }
            sum += findValue(str);
        }

        return sum;
    }

    public static int findValue(String in){
        char[] inChar = in.toCharArray();
        String out = "";
        char first = ' ';
        char last = ' ';

        for (char c : inChar){
            if (!Character.isDigit(c)) continue;
            if (first == ' '){
                first = c;
                continue;
            }

            last = c;
        }
        out += first;
        if (last == ' '){
            out += first;
            return Integer.parseInt(out);
        }
        out += last;
        return Integer.parseInt(out);
    }

    public static String cleanNums(String in){
        // has front bias so detects eightwo as 8wo so sum is wrong
        // could fix by going over everything looking for specific num word for each num word and remembering positions
        // then stitching together by position
        // can't be bothered tho
                 
        StringBuilder buff = new StringBuilder();
        StringBuilder out = new StringBuilder();

        for (char c : in.toCharArray()){
            if (Character.isDigit(c)){
                out.append(c);
                buff.setLength(0);
                continue;
            }
            buff.append(c);
            for (Entry<String,String> e : nums.entrySet()){
                if (buff.toString().contains(e.getKey())){
                    out.append(e.getValue());
                    buff.setLength(0);
                }
            }
        }

        return out.toString();
    }
}
