package com.hacker.rank.practice.algorithms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * Example:
 * The four players on the leaderboard have high scores of 100, 90, 90, and 80.
 * Those players will have ranks 1st, 2nd, 2nd, and 3rd, respectively.
 * If Alice's scores are 70, 80 and 105, her rankings after each game are 4th, 3rd and 1st.
 */

/*
 * Timed constraints
 */
public class Leaderboard
{

   /*
    * scores in descending order and return alice's rank in order of his scores in ascending order
    */
   static int[] climbingLeaderboard(int[] scores, int[] alice)
   {
      List<Integer> distinctScores = removeDuplicateScore(scores);
      int[] aliceRanks = new int[alice.length];
      if (distinctScores.size() <= 200)
      {
         for (int i = 0; i < alice.length; i++)
         {
            aliceRanks[i] = getRankUsingInsertionSort(distinctScores, alice[i]);
         }
      }
      else
      {
         for (int i = 0; i < alice.length; i++)
         {
            aliceRanks[i] = getRankUsingBinarySort(distinctScores, alice[i]);
         }
      }
      return aliceRanks;
   }

   static List<Integer> removeDuplicateScore(int[] scores)
   {
      return Arrays.stream(scores).distinct().boxed().collect(Collectors.toList());
   }

   static int getRankUsingBinarySort(List<Integer> scores, int aliceScore)
   {
      if (scores.size() == 0)
      {
         return 1;
      }
      if (scores.size() == 1)
      {
         return aliceScore >= scores.get(0) ? 1 : 2;
      }
      int index = getIndexUsingBinarySort(scores, aliceScore, 0, scores.size());
      return index + 1;
   }

   static int getIndexUsingBinarySort(List<Integer> scores, int aliceScore, int start, int end)
   {
      if (start >= end) // don't forget to use this check
         return start;

      int mid = (start + end) / 2; // do not use (end - start) / 2
      if (aliceScore == scores.get(mid))
         return mid;
      if (aliceScore > scores.get(mid))
         return getIndexUsingBinarySort(scores, aliceScore, start, mid);
      else
         return getIndexUsingBinarySort(scores, aliceScore, mid + 1, end);
   }

   static int getRankUsingInsertionSort(List<Integer> scores, int aliceScore)
   {
      int rank = 1;
      for (int score : scores)
      {
         if (aliceScore >= score)
         {
            break;
         }
         rank++;
      }
      return rank;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int scoresCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] scores = new int[scoresCount];

      String[] scoresItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < scoresCount; i++)
      {
         int scoresItem = Integer.parseInt(scoresItems[i]);
         scores[i] = scoresItem;
      }

      int aliceCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] alice = new int[aliceCount];

      String[] aliceItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < aliceCount; i++)
      {
         int aliceItem = Integer.parseInt(aliceItems[i]);
         alice[i] = aliceItem;
      }

      int[] result = climbingLeaderboard(scores, alice);

      for (int i = 0; i < result.length; i++)
      {
         bufferedWriter.write(String.valueOf(result[i]));

         if (i != result.length - 1)
         {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
