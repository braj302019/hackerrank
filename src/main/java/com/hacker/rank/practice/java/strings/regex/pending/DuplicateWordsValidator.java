package com.hacker.rank.practice.java.strings.regex.pending;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * regular expressions (RegEx) to remove instances of words that are repeated more than once,
 * but retain the first occurrence of any case-insensitive repeated word
 */
public class DuplicateWordsValidator
{

   // Special case (not implemented yet)
   // Input "tam tam ta tam tam"
   // Actual Output "tam ta tam"
   // Expected Output "tam ta"

   private static final String pattern = "(?i)\\b(\\w+)\\b(?:.*?)(\\s+\\b\\1\\b)+";

   private static String replaceDuplicateWords(String text)
   {
      return text.replaceAll(pattern, "$1");
   }

   private static List<String> readCountAndValues()
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine().trim());
         return IntStream.range(0, count).mapToObj(i -> reader.nextLine()).collect(Collectors.toList());
      }
   }

   public static void main(String[] args)
   {
      List<String> values = readCountAndValues();
      values.forEach(value -> System.out.println(replaceDuplicateWords(value)));
   }
}
