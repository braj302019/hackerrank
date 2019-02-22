package com.hacker.rank.practice.algorithms.pending;

/*
 * Given a set of distinct integers,
 * print the size of a maximal subset of S where the sum of any 2 numbers in S' is not evenly divisible by k.
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * Timed constraints
 */
// FIXME: failed due to time constraints, it should be done in O(n)
public class NonDivisibleSubset
{

   static int nonDivisibleSubset(int k, int[] S)
   {
      Map<Integer, Integer> frequencyByValue = new HashMap<>();
      Map<Integer, Set<Integer>> divisible = new HashMap<>();
      long start = System.currentTimeMillis();
      for (int i = 0; i < S.length - 1; i++)
      {
         for (int j = i + 1; j < S.length; j++)
         {
            int sum = S[i] + S[j];
            if (sum % k != 0)
            {
               frequencyByValue.put(S[i], frequencyByValue.getOrDefault(S[i], 0) + 1);
               frequencyByValue.put(S[j], frequencyByValue.getOrDefault(S[j], 0) + 1);
            }
            else
            {
               divisible.putIfAbsent(S[i], new HashSet<>());
               divisible.putIfAbsent(S[j], new HashSet<>());

               divisible.get(S[i]).add(S[j]);
               divisible.get(S[j]).add(S[i]);
            }
            System.out.println(S[i] + ", " + S[j] + " % " + (sum % k));
         }
      }
      System.out.println("time taken before:" + (System.currentTimeMillis() - start));
      start = System.currentTimeMillis();
      for (Map.Entry<Integer, Set<Integer>> e : divisible.entrySet())
      {
         Integer key = e.getKey();
         for (Integer value : e.getValue())
         {
            if (frequencyByValue.containsKey(key) && frequencyByValue.containsKey(value))
            {
               if (frequencyByValue.getOrDefault(key, 0) > frequencyByValue.getOrDefault(value, 0))
                  frequencyByValue.remove(value);
               else
                  frequencyByValue.remove(key);
            }
         }
      }
      System.out.println("time taken after:" + (System.currentTimeMillis() - start));
      return frequencyByValue.size();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] nk = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nk[0]);

      int k = Integer.parseInt(nk[1]);

      int[] S = new int[n];

      String[] SItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++)
      {
         int SItem = Integer.parseInt(SItems[i]);
         S[i] = SItem;
      }

      int result = nonDivisibleSubset(k, S);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
