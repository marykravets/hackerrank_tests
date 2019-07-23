package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solutions {

    public static int sockMerchant(int n, int[] ar) {
        HashMap<Integer, Integer> map = new HashMap();
        int size = 0, val;

        for (int i = 0; i < n; i++) {
            if (map.containsKey(ar[i])) {
                val = map.get(ar[i]);

                map.put(ar[i], ++val);
                if ((val & 1) == 0) {
                    size++;
                }
            } else {
                map.put(ar[i], 1);
            }
        }

        return size;
    }

    public static int countingValleys(int n, String s) {
        int seaLevel = 0, count = 0;
        for (int i = 0; i < n; i++) {

            if (s.charAt(i) == 'U') {
                seaLevel++;
            } else {
                if (seaLevel == 0) {
                    count++;
                }
                seaLevel--;
            }
        }

        return count;
    }

    public static int jumpingOnClouds(int[] c) {
        int jump = -1, len = c.length;
        for (int i = 0; i < len; i++, jump++) {
            if (i < len-2 && c[i+2] == 0) {
                i++;
            }
        }
        return jump;
    }

    // A method for merging 2 hashmaps into 1
    // if there is a map inside a map, then add it too.
    // for example:
    // "a", 0;   "b", {"b1", 1}
    // "a", 1;   "b", {"b2", 2}
    //
    //  result:
    // "a" { 0, 1 };  "b", {"b1",1; "b2",2};
    //
    public static HashMap<Object, Object> getMergedHashMap(Map<Object, Object> mapA, Map<Object, Object> mapB) {
        HashMap<Object, Object> mergedMap = new HashMap();
        ArrayList<Object> list;

        for (Object key: mapA.keySet()) {
            for (Object key2 : mapB.keySet()) {

                if (mergedMap.get(key2) == null) {
                    mergedMap.put(key2, mapB.get(key2));
                }
            }

            if (mergedMap.get(key) == null) {
                mergedMap.put(key, mapA.get(key));
            } else {
                Object o = mergedMap.get(key);
                list = new ArrayList<>();
                list.add(o);
                if (mapA.get(key) != null) {
                    list.add(mapA.get(key));
                }
                mergedMap.put(key, list);
            }
        }

        return mergedMap;
    }
}
