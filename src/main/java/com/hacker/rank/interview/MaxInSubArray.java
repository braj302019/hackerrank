//package com.hacker.rank.interview;
//
//import java.util.Scanner;
//import java.util.stream.IntStream;
//
//public class MaxInSubArray
//{
//   private static int[] readCountAndValues()
//   {
//      try (Scanner reader = new Scanner(System.in))
//      {
//         int count = Integer.parseInt(reader.nextLine().trim());
//         return IntStream.range(0, count).map(i -> reader.nextInt()).toArray();
//      }
//   }
//
//   private static int[] readCountAndRanges()
//   {
//      try (Scanner reader = new Scanner(System.in))
//      {
//         int count = Integer.parseInt(reader.nextLine().trim());
//         return IntStream.range(0, count).map(i -> new int[]{reader.nextInt(), reader.nextInt()}).toArray();
//      }
//   }
//
//   public static void main(String[] args)
//   {
//      int[] values = readCountAndValues();
//      meanMedianMode(values);
//   }
//}
