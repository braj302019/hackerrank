package com.hacker.rank.practice.algorithms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Lexicographical order is often known as alphabetical order when dealing with strings. A string is greater than another string if it comes later in a lexicographically sorted list.
 *
 * Given a word, create a new word by swapping some or all of its characters. This new word must meet two criteria:
 * - It must be greater than the original word
 * - It must be the smallest word that meets the first condition
 *
 * For example, given the word w=abcd, the next largest word is abdc.
 */
public class BiggerIsGreater
{
   static String biggerIsGreater(String w)
   {
      int d = -1;
      for (int i = w.length() - 1; i > 0; i--)
      {
         if (w.charAt(i - 1) < w.charAt(i))
         {
            d = i - 1;
            break;
         }
      }
      if (d == -1)
      {
         return "no answer";
      }
      char[] rightMostNumbers = new char[w.length() - d - 1];
      for (int i = 0; i < rightMostNumbers.length; i++)
      {
         rightMostNumbers[i] = w.charAt(i + d + 1);
      }
      char charToSwap = w.charAt(d);
      int smallest = 0;
      for (int i = 1; i < rightMostNumbers.length; i++)
      {
         if (rightMostNumbers[i] < rightMostNumbers[smallest] && rightMostNumbers[i] > charToSwap)
         {
            smallest = i;
         }
      }
      char smallestChar = rightMostNumbers[smallest];
      if (smallestChar == charToSwap)
      {
         return "no answer";
      }
      rightMostNumbers[smallest] = charToSwap;
      Arrays.sort(rightMostNumbers);
      return w.substring(0, d) + smallestChar + String.valueOf(rightMostNumbers);
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int T = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int TItr = 0; TItr < T; TItr++)
      {
         String w = scanner.nextLine();

         String result = biggerIsGreater(w);

         bufferedWriter.write(result);
         bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
   }
}
