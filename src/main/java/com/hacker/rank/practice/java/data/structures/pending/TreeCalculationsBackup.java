package com.hacker.rank.practice.java.data.structures.pending;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class TreeCalculationsBackup
{

   static long[] calculate(int n, int[][] lines, List<int[]> sets)
   {
      long time = System.currentTimeMillis();

      long modulusBy = (long) (Math.pow(10, 9) + 7);
      Graph graph = new Graph(n);

      // add edges
      for (int[] line : lines)
      {
         graph.addEdge(line[0] - 1, line[1] - 1);
      }

      // System.out.println("time taken in adding the edges:" + (System.currentTimeMillis() - time));
      time = System.currentTimeMillis();

      // find distances from each vertex
      BFSResultMetadata metadata = graph.getBFSResultMetadata(graph.vertices[0], graph.size);

      // System.out.println(metadata.parents);
      // System.out.println(metadata.distances);

      System.out.println("time taken to find the parents:" + (System.currentTimeMillis() - time));
      time = System.currentTimeMillis();

      // TODO Important Note: array is better than Map in terms of performance
      int[][] distances = new int[graph.size][];
      for (int i = 0; i < graph.size; i++)
      {
         distances[i] = new int[graph.size];
      }

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
               if (s == d)
               {
                  distances[s][d] = 0;
               }
               else if (distances[s][d] == 0 || distances[d][s] == 0)
               {
                  distances[s][d] = distances[d][s] = getDistance(s, d, metadata);
               }

               cal += ((s + 1) * (d + 1) * distances[s][d]);
            }
         }
         result[index++] = cal % modulusBy;
      }
      System.out.println("time taken in calculation:" + (System.currentTimeMillis() - time));
      return result;
   }

   static Integer getCorrectDistance(Integer s, Integer d, Map<Integer, Integer> parents)
   {
      int dstDistance = 0;
      Map<Integer, Integer> dstParentAndDistance = new HashMap<>();
      dstParentAndDistance.put(d, 0);
      Integer dstParent = d;
      while (dstParent != null)
      {
         if (dstParent == s)
         {
            return dstDistance;
         }
         dstParentAndDistance.put(dstParent, dstDistance);
         dstParent = parents.get(dstParent);
         dstDistance++;
      }

      int srcDistance = 0;
      Integer srcParent = s;
      while (srcParent != null)
      {
         if (dstParentAndDistance.containsKey(srcParent))
         {
            return dstParentAndDistance.get(srcParent) + srcDistance;
         }
         srcParent = parents.get(srcParent);
         srcDistance++;
      }
      return 0;
   }

   static Integer getDistance(Integer s, Integer d, BFSResultMetadata metadata)
   {

      if (s == d)
         return 0;

      int srcDistance = metadata.distances[s];
      int dstDistance = metadata.distances[d];

      Set<Integer> srcParents = metadata.parents.get(s);
      Set<Integer> dstParents = metadata.parents.get(d);

      int srcGrandParent = metadata.grandParents[s];
      int dstGrandParent = metadata.grandParents[d];

      if (dstParents.contains(s) || srcParents.contains(d)) // grandparents
      {
         return Math.abs(dstDistance - srcDistance);
      }
      else if (srcGrandParent == dstGrandParent) // siblings
      {
         return 2;
      }
      else
      {
         long intersectionSize = srcParents.size() < dstParents.size() ? srcParents.stream().filter(dstParents::contains).count()
            : dstParents.stream().filter(srcParents::contains).count();
         return srcParents.size() + dstParents.size() + 2 - (2 * (int) intersectionSize);
      }
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
         bufferedWriter.write(String.valueOf(result[i]));
         bufferedWriter.newLine();
      }

      bufferedWriter.close();
   }

   static class Graph
   {
      final int size;
      int edges;
      int[] vertices;
      List<Integer>[] adj;

      @SuppressWarnings("unchecked")
      Graph(int size)
      {
         this.size = size;
         this.adj = new ArrayList[size];
         this.vertices = new int[size];
         for (int i = 0; i < size; i++)
         {
            this.vertices[i] = i;
            this.adj[i] = new ArrayList<>();
         }
      }

      void addEdge(int u, int v)
      {
         edges++;

         adj[u].add(v);
         adj[v].add(u);
      }

      int[] getDistances(int s)
      {
         int[] distances = new int[size];
         Color[] colors = new Color[size];

         for (int v : vertices)
         {
            colors[v] = Color.WHITE;
            distances[v] = Integer.MAX_VALUE;
         }
         colors[s] = Color.GRAY;
         distances[s] = 0;

         Queue<Integer> queue = new LinkedList<>();
         queue.add(s);

         while (!queue.isEmpty())
         {
            int u = queue.poll();
            for (int v : adj[u])
            {
               if (colors[v] == Color.WHITE)
               {
                  colors[v] = Color.GRAY;
                  distances[v] = distances[u] + 1;
                  queue.add(v);
               }
            }
            colors[u] = Color.BLACK;
         }
         return distances;
      }

      BFSResultMetadata getBFSResultMetadata(int s, int size)
      {
         Map<Integer, Set<Integer>> parents = new HashMap<>();
         int[] grandParents = new int[size];
         int[] distances = new int[size];
         Color[] colors = new Color[size];

         for (int v : vertices)
         {
            colors[v] = Color.WHITE;
         }
         colors[s] = Color.GRAY;
         parents.put(s, new HashSet<>());
         distances[s] = 0;

         Queue<Integer> queue = new LinkedList<>();
         queue.add(s);

         while (!queue.isEmpty())
         {
            int u = queue.poll();
            for (int v : adj[u])
            {
               if (colors[v] == Color.WHITE)
               {
                  colors[v] = Color.GRAY;
                  parents.putIfAbsent(v, new HashSet<>());
                  parents.get(v).add(u);
                  parents.get(v).addAll(parents.get(u));
                  distances[v] = distances[u] + 1;
                  grandParents[v] = u;
                  queue.add(v);
               }
            }
            colors[u] = Color.BLACK;
         }
         BFSResultMetadata metadata = new BFSResultMetadata();
         metadata.distances = distances;
         metadata.parents = parents;
         metadata.grandParents = grandParents;
         return metadata;
      }
   }

   static enum Color
   {
      WHITE, GRAY, BLACK
   }

   static class BFSResultMetadata
   {
      Map<Integer, Set<Integer>> parents;
      int[] grandParents;
      int[] distances;
   }
}
