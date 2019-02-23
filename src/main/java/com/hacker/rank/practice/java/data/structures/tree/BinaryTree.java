package com.hacker.rank.practice.java.data.structures.tree;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 * Binary Tree operations
 */
public class BinaryTree
{
   Node root;

   static class Node
   {
      int val; // Value
      Node left; // Left child
      Node right; // Right child
      Node parent; // Parent
      int height; // Height
      int balanceFactor; // height(left subtree) - height(right subtree)

      Node(int val)
      {
         this.val = val;
      }
   }

   Node minimum(Node x)
   {
      while (x.left != null)
      {
         x = x.left;
      }
      return x;
   }

   Node maximum(Node x)
   {
      while (x.right != null)
      {
         x = x.right;
      }
      return x;
   }

   void inorderTraversal(Node x)
   {
      if (x != null)
      {
         inorderTraversal(x.left); // left
         System.out.print(x.val + "(" + x.height + "),"); // parent
         inorderTraversal(x.right); // right
      }
   }

   void preorderTraversal(Node x)
   {
      if (x != null)
      {
         System.out.print(x.val + "(" + x.height + "),"); // parent
         inorderTraversal(x.left); // left
         inorderTraversal(x.right); // right
      }
      System.out.println();
   }

   Node successor(Node x)
   {
      if (x.right != null)
         return minimum(x.right); // find the minimum in the right substree of the node

      Node y = x.parent;
      while (y != null && x != y.right) // find the parent
      {
         x = y;
         y = y.parent;
      }
      return y;
   }

   Node predecessor(Node x)
   {
      if (x.left != null)
         return maximum(x.left); // find the maximum in the left substree of the node

      Node y = x.parent;
      while (y != null && x != y.left) // find the parent
      {
         x = y;
         y = y.parent;
      }
      return y;
   }

   void transplant(Node u, Node y) // replace Node u subtree from Node v subtree
   {
      if (u.parent == null) // u was parent node of the tree
         root = y;
      else if (u.parent.left == u) // if u was left child in the tree
         u.parent.left = y;
      else
         u.parent.right = y;

      if (y != null) // set the parent
         y.parent = u.parent;
   }

   void delete(Node x)
   {
      // three use cases
      // 1. no child
      // 2. one child either in left or right
      // 3. two children
      if (x.left == null) // only one child
         transplant(x, x.right);
      else if (x.right == null) // only one child
         transplant(x, x.left);
      else // two children
      {
         Node y = minimum(x.right); // find successor
         if (y.parent != x) // special case
         {
            transplant(y, y.right);
            y.right = x.right;
            y.right.parent = y;
         }
         transplant(x, y);
         y.left = x.left;
         y.left.parent = y;
      }
   }

   void insert(Node x)
   {
      Node y = null;
      Node node = root;
      while (node != null)
      {
         y = node;

         if (x.val < node.val)
            node = node.left;
         else
            node = node.right;
      }

      x.parent = y; // set the parent

      if (y == null) // if tree was empty
         root = x;
      else if (x.val < y.val) // set as left child
         y.left = x;
      else // set as right child
         y.right = x;
   }

   Node search(int val)
   {
      Node node = root;
      while (node != null && node.val != val)
      {
         if (val < node.val)
            node = node.left;
         else
            node = node.right;
      }
      return node;
   }

   int maxLeftHeight(Node x)
   {
      int height = 0;
      while (x != null)
      {
         x = x.left;
         height++;
      }
      return height;
   }

   int maxRightHeight(Node x)
   {
      int height = 0;
      while (x != null)
      {
         x = x.right;
         height++;
      }
      return height;
   }

   int maxHeight(Node x)
   {
      return Math.max(maxLeftHeight(x), maxRightHeight(x));
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

   Node balanceTree()
   {
      List<Node> nodes = new ArrayList<>();
      inorderTraversal(root, nodes);
      return buildTree(nodes, 0, nodes.size() - 1);
   }

   void showTree(int treeHeight)
   {
      DefaultMutableTreeNode top = new DefaultMutableTreeNode("Original Binary Tree");
      preorderTraversal(root, top);
      JTree tree = new JTree(top);
      JScrollPane treeView = new JScrollPane(tree);
      JFrame jFrame = new JFrame();

      JButton balanceTree = new JButton("Balance Tree");
      balanceTree.addActionListener((e) -> {
         Node root = balanceTree();
         DefaultMutableTreeNode node = new DefaultMutableTreeNode("Balanced Binary Tree");
         preorderTraversal(root, node);
         JTree jtree = new JTree(node);
         expandAll(jtree);
         jFrame.add(new JScrollPane(jtree), BorderLayout.EAST);
         jFrame.revalidate();
         jFrame.repaint();
         balanceTree.setEnabled(false);

         setHeightAndBalanceFactor(root, treeHeight);
         System.out.println();
         preorderTraversal(root);
      });

      jFrame.add(treeView, BorderLayout.WEST);
      jFrame.add(balanceTree, BorderLayout.SOUTH);
      jFrame.setSize(500, 500);
      jFrame.setVisible(true);
      expandAll(tree);
      jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }

   void expandAll(JTree tree)
   {
      for (int i = 0; i < tree.getRowCount(); i++)
      {
         tree.expandRow(i);
      }
   }

   void preorderTraversal(Node x, DefaultMutableTreeNode node)
   {
      if (x != null)
      {
         DefaultMutableTreeNode parent = new DefaultMutableTreeNode(x.val); // parent
         node.add(parent);
         preorderTraversal(x.left, parent); // left
         preorderTraversal(x.right, parent); // right
      }
   }

   void setHeightAndBalanceFactor(Node node, int height)
   {
      if (node != null)
      {
         node.height = height;
         node.balanceFactor = maxHeight(node.left) - maxHeight(node.right);
         setHeightAndBalanceFactor(node.left, height - 1);
         setHeightAndBalanceFactor(node.right, height - 1);
      }
   }

   void leftRotate(Node x)
   {
      Node y = x.right; // set y
      x.right = y.left; // set y'left subtree into x'right subtree

      if (y.left != null)
         y.left.parent = x;

      y.parent = x.parent; // link x's parent to y

      if (x.parent == null)
         root = y;
      else if (x.parent.left == x)
         x.parent.left = y;
      else
         x.parent.right = y;

      y.left = x; // put x on y's left
      x.parent = y;
   }

   void rightRotate(Node x)
   {
      Node y = x.left;
      x.left = y.right;

      if (y.right != null)
         y.right.parent = x;

      y.parent = x.parent;

      if (x.parent == null)
         root = y;
      else if (x.parent.left == x)
         x.parent.left = y;
      else
         x.parent.right = y;

      y.right = x;
      x.parent = y;
   }

   static int treeHeight(int n)
   {
      return (int) Math.floor(Math.log(n) / Math.log(2)); // log2(m) = log(m) / log(2)
   }

   public static void main(String[] args)
   {
      BinaryTree tree = new BinaryTree();
      int[] values = new int[] { 3, 2, 4, 5, 6 };
      for (int val : values)
      {
         Node node = new Node(val);
         tree.insert(node);
      }

      int treeHeight = treeHeight(values.length);
      tree.setHeightAndBalanceFactor(tree.root, treeHeight);
      System.out.println();
      tree.preorderTraversal(tree.root);

      tree.showTree(treeHeight);
   }
}
