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
        //System.out.println(cleanNums("two1nine"));
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
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < in.length(); i++){
            if (Character.isDigit(in.charAt(i))){
                out.append(in.charAt(i));
                continue;
            }
            for (Entry<String, String> e : nums.entrySet()){
                if (!in.substring(i).startsWith(e.getKey())) continue;
                out.append(e.getValue());
            }

        }
        return out.toString();
    }
}
