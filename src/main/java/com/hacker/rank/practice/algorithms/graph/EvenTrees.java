package com.hacker.rank.practice.algorithms.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * Find the maximum number of edges you can remove from the tree to get a forest
 * such that each connected component of the forest contains an even number of nodes.
 */
public class EvenTrees
{

   private static final Logger logger = Logger.getGlobal();

   static enum Color
   {
      WHITE, GRAY, BLACK;
   }

   static class Vertex
   {
      int value;
      Color color;
      Vertex parent;
      int weight;

      Vertex(int value)
      {
         this.value = value;
      }

      @Override
      public boolean equals(Object other)
      {
         if (other instanceof Vertex)
         {
            return this.value == ((Vertex) other).value;
         }
         return false;
      }

      @Override
      public int hashCode()
      {
         return Integer.valueOf(value).hashCode();
      }
   }

   static class Edge
   {
      Vertex from;
      Vertex to;
      int weight;

      Edge(Vertex from, Vertex to)
      {
         this.from = from;
         this.to = to;
      }
   }

   static class Graph
   {
      Vertex[] vertices;
      List<List<Vertex>> adjacencyList;

      Graph(int verticesCount)
      {
         vertices = new Vertex[verticesCount];
         for (int i = 0; i < verticesCount; i++)
         {
            vertices[i] = new Vertex(i + 1);
         }

         adjacencyList = new ArrayList<>();
         for (int i = 0; i < verticesCount; i++)
         {
            adjacencyList.add(new ArrayList<>());
         }
      }

      void addEdge(int u, int v)
      {
         adjacencyList.get(u - 1).add(vertices[v - 1]);
         adjacencyList.get(v - 1).add(vertices[u - 1]);
      }

      List<Vertex> adjacencyList(Vertex u)
      {
         return adjacencyList.get(u.value - 1);
      }
   }

   static class DepthFirstTree
   {
      static int depthFirstSearch(Graph graph)
      {
         for (Vertex u : graph.vertices)
         {
            u.color = Color.WHITE;
            u.parent = null;
            u.weight = 0;
         }

         List<Edge> evenWeightEdges = new ArrayList<>();
         for (Vertex u : graph.vertices)
         {
            if (u.color == Color.WHITE)
            {
               depthFirstSearchVisit(graph, u, evenWeightEdges);
            }
         }

         logger.info("Even weight edges:" + evenWeightEdges.size());
         for (Edge edge : evenWeightEdges)
         {
            logger.info(edge.from.value + "->" + edge.to.value + " = " + edge.weight);
         }
         return evenWeightEdges.size();
      }

      static void depthFirstSearchVisit(Graph graph, Vertex u, List<Edge> evenWeightEdges)
      {
         u.weight = 1;
         u.color = Color.GRAY;
         for (Vertex v : graph.adjacencyList(u))
         {
            if (v.color == Color.WHITE)
            {
               v.parent = u;
               depthFirstSearchVisit(graph, v, evenWeightEdges);
               u.weight += v.weight;
            }
         }
         u.color = Color.BLACK;
         if (u.weight % 2 == 0 && u.parent != null)
         {
            Edge edge = new Edge(u.parent, u);
            edge.weight = u.weight;
            evenWeightEdges.add(edge);
         }
      }
   }

   static void showTree(Graph graph)
   {
      DefaultMutableTreeNode top = new DefaultMutableTreeNode("Tree");
      JTree tree = new JTree(top);
      addNodes(graph, top);
      JScrollPane treeView = new JScrollPane(tree);
      JFrame jFrame = new JFrame();

      jFrame.add(treeView);
      jFrame.setSize(500, 500);
      jFrame.setVisible(true);
      expandAll(tree);
      jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   static void addNodes(Graph graph, DefaultMutableTreeNode parent)
   {
      for (Vertex u : graph.vertices)
      {
         u.color = Color.WHITE;
         u.parent = null;
      }
      for (Vertex u : graph.vertices)
      {
         if (u.color == Color.WHITE)
         {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(u.value);
            parent.add(node);
            addNode(graph, u, node);
         }
      }
   }

   static void addNode(Graph graph, Vertex u, DefaultMutableTreeNode parent)
   {
      u.color = Color.GRAY;
      for (Vertex v : graph.adjacencyList(u))
      {
         if (v.color == Color.WHITE)
         {
            v.parent = u;
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(v.value);
            parent.add(node);
            addNode(graph, v, node);
         }
      }
      u.color = Color.BLACK;
   }

   static void expandAll(JTree tree)
   {
      for (int i = 0; i < tree.getRowCount(); i++)
      {
         tree.expandRow(i);
      }
   }

   static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to)
   {
      Graph graph = new Graph(t_nodes);
      for (int i = 0; i < t_edges; i++)
      {
         graph.addEdge(t_from.get(i), t_to.get(i));
      }
      // showTree(graph);

      int evenWeightEdgesCount = DepthFirstTree.depthFirstSearch(graph);
      return evenWeightEdgesCount;
   }

   public static void main(String[] args) throws IOException
   {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

      int tNodes = Integer.parseInt(tNodesEdges[0]);
      int tEdges = Integer.parseInt(tNodesEdges[1]);

      List<Integer> tFrom = new ArrayList<>();
      List<Integer> tTo = new ArrayList<>();

      IntStream.range(0, tEdges).forEach(i -> {
         try
         {
            String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            tFrom.add(Integer.parseInt(tFromTo[0]));
            tTo.add(Integer.parseInt(tFromTo[1]));
         }
         catch (IOException ex)
         {
            throw new RuntimeException(ex);
         }
      });

      int res = evenForest(tNodes, tEdges, tFrom, tTo);

      bufferedWriter.write(String.valueOf(res));
      bufferedWriter.newLine();

      bufferedReader.close();
      bufferedWriter.close();
   }
}
