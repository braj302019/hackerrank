package com.hacker.rank.practice.java.data.structures.pending;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/*
 * You are given a list of N numbers a1,a2,...an. For each element at position i (1<=i<=N), we define Left(i) and Right(i) as:
 * Left(i) = closest index j such that j < i and aj > ai. If no such j exists then Left(i) = 0.
 * Right(i) = closest index k such that k > i and ak > ai. If no such k exists then Right(i) = 0.
 * We define IndexProduct(i) = Left(i) * Right(i). You need to find out the maximum IndexProduct(i) among all i
 */
public class MaximulIndexProductBackup
{
   static class BST
   {
      static Node root;

      void insert(Node z)
      {
         Node y = null;
         Node x = root;
         while (x != null)
         {
            y = x;
            if (z.key < x.key)
               x = x.left;
            else
               x = x.right;
         }

         if (y == null) // if tree is empty
            root = z;
         else if (z.key < y.key)
            y.left = z;
         else
            y.right = z;
      }

      Node extractLeastMax(Node x)
      {
         if (x.right != null)
            return extractMinimum(x.right);
         else if (x.left != null)
            return extractMaximum(x.left);
         return null;
      }

      Node extractMinimum(Node x)
      {
         while (x != null && x.left != null)
            x = x.left;
         return x;
      }

      Node extractMaximum(Node x)
      {
         while (x != null && x.right != null)
            x = x.right;
         return x;
      }

      void inOrderTraversal(Node z)
      {
         if (z != null)
         {
            inOrderTraversal(z.left);
            System.out.println(z.left + " <- " + z + " -> " + z.right);
            inOrderTraversal(z.right);
         }
      }

      Node search(Node root, int key)
      {
         if (root == null || key == root.key)
            return root;

         if (key < root.key)
            return search(root.left, key);
         else
            return search(root.right, key);
      }

      Node buildBalancedBinaryTree(int[] nodes, int start, int end)
      {
         // base case
         if (start > end)
            return null;

         /* Get the middle element and make it root */
         int mid = (start + end) / 2;
         Node node = new Node(nodes[mid], mid);

         /*
          * Using index in Inorder traversal, construct left and right subtress
          */
         node.left = buildBalancedBinaryTree(nodes, start, mid - 1);
         node.right = buildBalancedBinaryTree(nodes, mid + 1, end);

         return node;
      }
   }

   static class Node
   {
      int data;
      int key;
      Node left;
      Node right;
      Node parent;

      Node(int data, int key)
      {
         this.data = data;
         this.key = key;
      }

      @Override
      public String toString()
      {
         return "[" + key + "," + data + "]";
      }
   }

   static int solve(int[] arr)
   {
      if (arr.length < 3)
         return 0;

      BST bst = new BST();
      BST.root = bst.buildBalancedBinaryTree(arr, 0, arr.length - 1);
      bst.inOrderTraversal(BST.root);

      int maxIndexProduct = 0;
      for (int i = 0; i < arr.length; i++)
      {
         int indexProduct = indexProduct(bst, i);
         if (indexProduct > maxIndexProduct)
         {
            maxIndexProduct = indexProduct;
         }
      }
      return maxIndexProduct;
   }

   static int indexProduct(BST bst, int key)
   {
      Node x = bst.search(BST.root, key);
      System.out.println("extracting left max of " + x.data);
      Node leftMax = bst.extractMaximum(x.left);
      if (leftMax.key != x.key)
      {
         System.out.println(leftMax.data);
         Node y = bst.search(BST.root, key);
         if (y != null)
         {
            System.out.println("extracting right max of " + y.data);
            Node rightMax = bst.extractMaximum(y.right);
            if (rightMax.key != y.key)
            {
               System.out.println(leftMax.data);
               return (leftMax.key + 1) * (rightMax.key + 1);
            }
         }
      }
      return 0;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException
   {
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

      int arrCount = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] arr = new int[arrCount];

      String[] arrItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int arrItr = 0; arrItr < arrCount; arrItr++)
      {
         int arrItem = Integer.parseInt(arrItems[arrItr]);
         arr[arrItr] = arrItem;
      }

      int result = solve(arr);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
