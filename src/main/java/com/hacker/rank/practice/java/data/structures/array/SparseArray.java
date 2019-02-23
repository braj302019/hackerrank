package com.hacker.rank.practice.java.data.structures.array;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SparseArray
{

   // Complete the matchingStrings function below.
   static int[] matchingStrings(String[] strings, String[] queries)
   {
      Map<String, Integer> matches = new HashMap<>();
      for (String string : strings)
      {
         matches.put(string, matches.getOrDefault(string, 0) + 1);
      }
      int[] count = new int[queries.length];
      for (int i = 0; i < queries.length; i++)
      {
         count[i] = matches.getOrDefault(queries[i], 0);
      }
      return count;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int stringsCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String[] strings = new String[stringsCount];

      for (int i = 0; i < stringsCount; i++)
      {
         String stringsItem = scanner.nextLine();
         strings[i] = stringsItem;
      }

      int queriesCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String[] queries = new String[queriesCount];

      for (int i = 0; i < queriesCount; i++)
      {
         String queriesItem = scanner.nextLine();
         queries[i] = queriesItem;
      }

      int[] res = matchingStrings(strings, queries);

      for (int i = 0; i < res.length; i++)
      {
         bufferedWriter.write(String.valueOf(res[i]));

         if (i != res.length - 1)
         {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
