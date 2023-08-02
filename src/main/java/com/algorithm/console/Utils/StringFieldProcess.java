package com.algorithm.console.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringFieldProcess {
    public static String normalizeField(String field) {
        Stack<String> words = new Stack<>();

        int find = Math.max(field.lastIndexOf("_"), Math.max(field.lastIndexOf("-"), field.lastIndexOf(" ")));
        while(find != -1) {
            if(find + 1 < field.length()) {
                String word = field.substring(find + 1);
                words.push(word.trim());
            }
            field = field.substring(0, find);
            find = Math.max(field.lastIndexOf("_"), field.lastIndexOf("-"));
        }
        if(field.length() > 0) {
            words.push(field.trim());
        }

        StringBuilder sb = new StringBuilder();

        while(!words.isEmpty()) {
            String currStr = words.pop();
            int prev = 0;

            for(int i = 0; i < currStr.length(); i++) {
                if(Character.isUpperCase(currStr.charAt(i)) && prev != i) {
                    String loadingStr = currStr.substring(prev, i);
                    sb.append(loadingStr.trim());
                    sb.append(' ');
                    prev = i;
                }
            }
            String loadingStr = currStr.substring(prev);
            sb.append(loadingStr.trim());
            sb.append(' ');
        }

        return sb.toString().trim().toLowerCase();
    }
}
