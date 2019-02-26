package com.udacity.sandwichclub.utils;

import java.util.Iterator;
import java.util.List;

public class StringUtils {

    public static String listOfStringsToString(List<String> listOfStrings) {
        StringBuilder builder = new StringBuilder(listOfStrings.size());
        for (Iterator<String> it = listOfStrings.iterator(); it.hasNext(); ) {
            builder.append(it.next());
            if (it.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
