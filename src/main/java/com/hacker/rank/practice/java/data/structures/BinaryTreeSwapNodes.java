package com.hacker.rank.practice.java.data.structures;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTreeSwapNodes
{
   static int[][] swapNodes(int[][] indexes, int[] queries)
   {
      int[][] result = new int[queries.length][];
      BinaryTree tree = buildBinaryTree(indexes);

      int index = 0;
      for (int level : queries)
      {
         List<Node> nodes = tree.getNodesAtLevel(level);
         int multiplier = 1;
         while (!nodes.isEmpty())
         {
            for (Node node : nodes)
            {
               tree.swapChildNodes(node);
            }
            multiplier++;
            nodes = tree.getNodesAtLevel(multiplier * level);
         }
         result[index++] = tree.inOrderTraversel();
      }
      return result;
   }

   private static BinaryTree buildBinaryTree(int[][] indexes)
   {
      BinaryTree tree = new BinaryTree();
      Node root = Node.root;
      Queue<Node> queue = new LinkedList<>();
      queue.add(root);
      for (int i = 0; i < indexes.length; i++)
      {
         Node parent = queue.poll();
         int left = indexes[i][0];
         int right = indexes[i][1];
         if (left != -1)
         {
            Node node = tree.addLeftNode(parent, left);
            queue.add(node);
         }
         if (right != -1)
         {
            Node node = tree.addRightNode(parent, right);
            queue.add(node);
         }
      }
      return tree;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(scanner.nextLine().trim());

      int[][] indexes = new int[n][2];

      for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++)
      {
         String[] indexesRowItems = scanner.nextLine().split(" ");

         for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++)
         {
            int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
            indexes[indexesRowItr][indexesColumnItr] = indexesItem;
         }
      }

      int queriesCount = Integer.parseInt(scanner.nextLine().trim());

      int[] queries = new int[queriesCount];

      for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++)
      {
         int queriesItem = Integer.parseInt(scanner.nextLine().trim());
         queries[queriesItr] = queriesItem;
      }

      int[][] result = swapNodes(indexes, queries);

      for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++)
      {
         for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++)
         {
            bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

            if (resultColumnItr != result[resultRowItr].length - 1)
            {
               bufferedWriter.write(" ");
            }
         }

         if (resultRowItr != result.length - 1)
         {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();
   }

   static class BinaryTree
   {
      void swapChildNodes(Node node)
      {
         Node temp = node.left;
         node.left = node.right;
         node.right = temp;
      }

      List<Node> getNodesAtLevel(int level)
      {
         List<Node> nodes = new ArrayList<>();

         Stack<Node> stack = new Stack<>();
         Node current = Node.root;
         while (current != null || !stack.isEmpty())
         {
            while (current != null && current.level <= level)
            {
               stack.push(current);
               current = current.left;
            }
            if (stack.isEmpty())
            {
               break;
            }
            current = stack.pop();
            if (current.level == level)
            {
               nodes.add(current);
            }
            current = current.right;
         }
         return nodes;
      }

      int[] inOrderTraversel()
      {
         List<Integer> values = new ArrayList<>();
         Stack<Node> stack = new Stack<>();
         Node current = Node.root;
         while (current != null || !stack.isEmpty())
         {
            while (current != null)
            {
               stack.push(current);
               current = current.left;
            }
            current = stack.pop();
            values.add(current.key);
            current = current.right;
         }
         return values.stream().mapToInt(Integer::intValue).toArray();
      }

      Node addLeftNode(Node parent, int key)
      {
         Node node = new Node(key);
         node.level = parent.level + 1;
         parent.left = node;
         return node;
      }

      Node addRightNode(Node parent, int key)
      {
         Node node = new Node(key);
         node.level = parent.level + 1;
         parent.right = node;
         return node;
      }

   }

   static class Node
   {
      static final Node root = createRootNode();

      int level = 1;
      Node left;
      Node right;
      Integer key;

      Node()
      {}

      Node(int key)
      {
         this.key = key;
      }

      static Node createRootNode()
      {
         return new Node(1);
      }

      @Override
      public String toString()
      {
         return String.valueOf(key);
      }
   }
}
