package com.example.demo.service;

import com.example.demo.model.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

public class BinaryTree {

    @Getter
    public Node root;

    @Getter
    private final List<Integer> listTraversed = new ArrayList<>();

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {

        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }
    private int getSizeRecursive(Node current) {
        return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    }

    public int getSizeFromCurrentNode(int value) {
        Node node = getNode(value);
        return getSizeRecursive(node);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }

        return value < current.value
          ? containsNodeRecursive(current.left, value)
          : containsNodeRecursive(current.right, value);
    }

    public Node getNode(int value) {
        return getAnyNodeRecursive(root,value);
    }
    private Node getAnyNodeRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            return current;
        }

        if (value < current.value) {
            current = current.left;
        } else {
            current = current.right;
        }
        return getAnyNodeRecursive(current,value);
    }

    public Node getParentNode(Node node) {
        return getParentNodeRecursive(root,node);
    }
    private Node getParentNodeRecursive(Node  parent,Node current) {

        if (parent.value == current.value) {
            return parent;
        }

        if (Objects.nonNull(parent.right)) {
            if (parent.right.value == current.value) {
                return parent;
            }
        }

        if (Objects.nonNull(parent.left)) {
            if (parent.left.value == current.value) {
                return parent;
            }
        }

        if (parent.value > current.value) {
            parent = parent.left;
        } else {
            parent = parent.right;
        }
        return getParentNodeRecursive(parent,current);
    }


    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            listTraversed.add(node.value);
            visit(node.value);
            traverseInOrder(node.right);
        }
    }

   private void visit(int value) {
        System.out.print(" " + value);
    }
}
