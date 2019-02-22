package com.hacker.rank.practice.java.data.structures.pending;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/*
 * TODO Important Note:
 * array is better than Map in terms of performance if index is the key
 */
public class TreeCalculations
{
   static final Logger logger = Logger.getGlobal();

   static long[] calculate(int n, int[][] lines, List<int[]> sets)
   {
      long time = System.currentTimeMillis();

      Graph graph = new Graph(n);
      for (int[] line : lines)
      {
         graph.addEdge(line[0] - 1, line[1] - 1);
      }

      logger.info("time taken in building the graph:" + (System.currentTimeMillis() - time));
      time = System.currentTimeMillis();

      long modulusBy = (long) (Math.pow(10, 9) + 7);
      long[] result = new long[sets.size()];
      int index = 0;
      for (int[] set : sets)
      {
         long cal = 0;
         for (int i = 0; i < set.length - 1; i++)
         {
            int s = set[i] - 1;
            for (int j = i + 1; j < set.length; j++)
            {
               int d = set[j] - 1;
               cal += ((s + 1) * (d + 1) * getDistance(s, d, graph));
            }
         }
         result[index++] = cal % modulusBy;
      }
      logger.info("time taken in calculation:" + (System.currentTimeMillis() - time));
      return result;
   }

   static int getDistance(int s, int d, Graph graph)
   {
      return 0;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = scanner.nextInt();
      int q = scanner.nextInt();

      int[][] lines = new int[n - 1][2];
      for (int i = 0; i < n - 1; i++)
      {
         lines[i] = new int[] { scanner.nextInt(), scanner.nextInt() };
      }

      List<int[]> sets = new ArrayList<>();
      for (int i = 0; i < q; i++)
      {
         int k = scanner.nextInt();
         int[] set = new int[k];
         for (int j = 0; j < k; j++)
         {
            set[j] = scanner.nextInt();
         }
         sets.add(set);
      }

      long[] result = calculate(n, lines, sets);
      for (int i = 0; i < result.length; i++)
      {
         // bufferedWriter.write(String.valueOf(result[i]));
         // bufferedWriter.newLine();
      }

      bufferedWriter.close();
   }

   static class Graph
   {
      final int size;
      final List<Integer>[] adj;

      @SuppressWarnings("unchecked")
      Graph(int size)
      {
         this.size = size;
         this.adj = new ArrayList[size];
         for (int i = 0; i < size; i++)
         {
            this.adj[i] = new ArrayList<>();
         }
      }

      void addEdge(int u, int v)
      {
         adj[u].add(v);
         adj[v].add(u);
      }
   }
}
