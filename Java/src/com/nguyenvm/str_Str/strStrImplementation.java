package com.nguyenvm.str_Str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/implement-strstr/
public class strStrImplementation {
    public static List<Integer> strStr(String haystack, String needle) {
        int rLength = haystack.length();
        int pLength = needle.length();
        if (pLength == 0) return Arrays.asList(0);
        if (pLength > rLength) return Arrays.asList(-1);

        int current = pLength - 1;
        List<Integer> results = new ArrayList<>();
        while (current < rLength) {
            if (haystack.charAt(current) != needle.charAt(pLength - 1)) {
                current++;
                continue;
            }

            int last = current;
            int first = current - (pLength - 1);
            int pLast = pLength - 1;
            int pFirst = 0;
            while (first <= last) {
                if (haystack.charAt(first) != needle.charAt(pFirst) || haystack.charAt(last) != needle.charAt(pLast)) {
                    current++;
                    break;
                }

                if (haystack.charAt(first) == needle.charAt(pFirst)) {
                    first++;
                    pFirst++;
                }

                if (haystack.charAt(last) == needle.charAt(pLast)) {
                    last--;
                    pLast--;
                }

                if (first > last) {
                    results.add(current - (pLength - 1));
                    current++;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        strStr("mississippiissip", "issip");
    }
}
