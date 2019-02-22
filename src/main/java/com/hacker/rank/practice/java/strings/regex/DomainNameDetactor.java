package com.hacker.rank.practice.java.strings.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DomainNameDetactor
{
   private static final String PATTERN_DOMAIN_NAME = "https?://(ww[w2].)?([a-zA-Z0-9-.]+\\.[a-zA-Z0-9-.]+)";
   private static final Pattern PATTERN = Pattern.compile(PATTERN_DOMAIN_NAME);

   private static List<String> readCountAndValues()
   {
      try (Scanner reader = new Scanner(System.in))
      {
         int count = Integer.parseInt(reader.nextLine());
         return IntStream.range(0, count).mapToObj(i -> reader.nextLine()).collect(Collectors.toList());
      }
   }

   public static List<String> extractDomainNames(String line)
   {
      List<String> names = new ArrayList<>();
      Matcher matcher = PATTERN.matcher(line);
      while (matcher.find())
      {
         names.add(matcher.group(2));
      }
      return names;
   }

   public static void main(String[] args)
   {
      List<String> values = readCountAndValues();
      String result = values
         .stream()
         .map(v -> extractDomainNames(v))
         .flatMap(List::stream)
         .filter(v -> !v.isEmpty())
         .distinct()
         .sorted()
         .collect(Collectors.joining(";"));
      System.out.println(result);
   }

}
