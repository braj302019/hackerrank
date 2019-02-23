package com.hacker.rank.practice.java.data.structures.tree;

import java.util.ArrayList;
import java.util.List;

public class BalancedTree
{
   static Node root;
   static class Node
   {
      int val; // Value
      Node left; // Left child
      Node right; // Right child
      Node parent; // Parent
   }

   Node balanceTree()
   {
      List<Node> nodes = new ArrayList<>();
      inorderTraversal(root, nodes);
      return buildTree(nodes, 0, nodes.size() - 1);
   }

   void inorderTraversal(Node x, List<Node> nodes)
   {
      if (x != null)
      {
         inorderTraversal(x.left, nodes); // left
         nodes.add(x); // parent
         inorderTraversal(x.right, nodes); // right
      }
   }

   Node buildTree(List<Node> nodes, int start, int end)
   {
      if (start > end)
         return null;

      int mid = (start + end) / 2;
      Node node = nodes.get(mid);

      node.left = buildTree(nodes, start, mid - 1);
      node.right = buildTree(nodes, mid + 1, end);
      return node;
   }
}
