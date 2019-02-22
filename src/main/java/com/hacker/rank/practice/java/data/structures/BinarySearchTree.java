package com.hacker.rank.practice.java.data.structures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree
{
   static Node root;

   static class Node
   {
      int data;
      Node left;
      Node right;

      Node(int data)
      {
         this.data = data;
      }
   }

   boolean checkBST(Node root)
   {
      List<Integer> list = new ArrayList<>();
      inOrderTraversal(root, list);

      for (int i = 0; i < list.size() - 1; i++)
      {
         if (list.get(i) >= list.get(i + 1))
            return false;
      }
      return true;
   }

   void inOrderTraversal(Node z, List<Integer> list)
   {
      if (z != null)
      {
         inOrderTraversal(z.left, list);
         list.add(z.data);
         inOrderTraversal(z.right, list);
      }
   }

   void insert(Node z)
   {
      Node y = null;
      Node x = root;
      while (x != null)
      {
         y = x;
         if (z.data < x.data)
            x = x.left;
         else
            x = x.right;
      }

      if (y == null) // if tree is empty
         root = z;
      else if (z.data < y.data)
         y.left = z;
      else
         y.right = z;
   }

   void inOrderTraversal(Node z)
   {
      if (z != null)
      {
         inOrderTraversal(z.left);
         System.out.println(z.data);
         inOrderTraversal(z.right);
      }
   }

   public static void main(String[] args) throws IOException
   {
      BinarySearchTree bst = new BinarySearchTree();

      int[] data = new int[] { 5, 4, 3, 4, 5 };
      for (int i : data)
      {
         bst.insert(new Node(i));
      }
      bst.inOrderTraversal(BinarySearchTree.root);
      System.out.println(bst.checkBST(root));
   }
}
