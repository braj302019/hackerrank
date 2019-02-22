package com.hacker.rank.practice.java.strings.regex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HtmlLinkExtractor
{
   private static final String PATTERN_OUTSIDE_LINK = "^.*?(?=<a)|(?<=<\\/a>).*?(?=<a)|(?<=<\\/a>).*?$|^.*$";
   private static final String PATTERN_LINK = "<a .*?\\bhref=(['\"])([^\1]+?)\\1[^>]*>\\s*(.*?)\\s*</a>";
   private static final String PATTERN_LINK_SPLITTER = "(?<=</a>)(?=<a )";
   private static final String PATTERN_TAG = "<[^>]+?>";

   private static List<String> readCountAndValues()
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine());
         return IntStream.range(0, count).mapToObj(i -> reader.nextLine()).collect(Collectors.toList());
      }
   }

   public static List<String> extractLinkAndNames(String line)
   {
      String[] links = line.split(PATTERN_LINK_SPLITTER);
      return Arrays.asList(links).stream().map(l -> l.replaceAll(PATTERN_LINK, "$2,$3").replaceAll(PATTERN_TAG, "")).collect(Collectors.toList());
   }

   public static void main(String[] args)
   {
      List<String> values = readCountAndValues();
      values
         .stream()
         .map(v -> v.replaceAll(PATTERN_OUTSIDE_LINK, ""))
         .filter(v -> !v.isEmpty())
         .map(v -> extractLinkAndNames(v))
         .flatMap(List::stream)
         .filter(v -> !v.isEmpty())
         .forEach(value -> System.out.println(value));
   }

}
