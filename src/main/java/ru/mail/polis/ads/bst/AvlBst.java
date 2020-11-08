package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root,key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, Key key) {
        if (node == null)
            return null;
        if (node.key.compareTo(key) > 0)
            return get(node.left, key);
        if (node.key.compareTo(key) < 0)
            return get(node.right, key);
        return node;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (node.key.compareTo(key) > 0) {
            node.left = put(node.left, key, value);
        }
        else if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0)
                node.left = rotateL(node.left);
            return rotateR(node);
        }
        else if (factor(node) == -2) {
            if (factor(node.right) > 0)
                node.right= rotateR(node.right);
            return rotateL(node);
        }
        return node;
    }

    private Node rotateR(Node node) {
        Node helpNode = node.left;
        node.left = helpNode.right;
        helpNode.right = node;
        fixHeight(node);
        fixHeight(helpNode);
        return helpNode;
    }

    private Node rotateL(Node node) {
        Node helpNode = node.right;
        node.right = helpNode.left;
        helpNode.left = node;
        fixHeight(node);
        fixHeight(helpNode);
        return helpNode;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node removed = get(root, key);
        if (removed == null) {
            return null;
        }
        size--;
        root = remove(root, key);
        return removed.value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            node = innerRemove(node);
        }
        return node;
    }

    private Node innerRemove(Node node) {
        if (node.right == null) return node.left;
        if (node.left == null) return node.right;
        Node temp = node;
        node = min(temp.right);
        node.right = removeMin(temp.right);
        node.left = temp.left;
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        return node.left == null ? node : min(node.left);
    }

    @Override
    public Key min() {
        Node node = min(root);
        return node == null ? null: node.key;
    }

    @Override
    public Value minValue() {
        Node node = min(root);
        return node == null? null: node.value;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        return node.right == null ? node : max(node.right);
    }

    @Override
    public Key max() {
        Node node = max(root);
        return node == null? null : node.key;
    }


    @Override
    public Value maxValue() {
        Node node = max(root);
        return node == null? null : node.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }

    private Key floor(Node node, Key key, Key maxKey) {
        if (node == null)
            return maxKey;
        if (node.key.compareTo(key) > 0) {
            maxKey = floor(node.left, key, maxKey);
        } else if (node.key.compareTo(key) < 0) {
            if (maxKey == null|| maxKey.compareTo(node.key) < 0) {
                maxKey = node.key;
            }
            maxKey = floor(node.right, key, maxKey);
        } else{
            maxKey = node.key;
        }
        return maxKey;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }

    private Key ceil(Node node, Key key, Key minKey) {
        if (node == null)
            return minKey;
        if (node.key.compareTo(key) > 0) {
            if (minKey == null|| minKey.compareTo(node.key) > 0) {
                minKey = node.key;
            }
            minKey = ceil(node.left, key, minKey);
        } else if (node.key.compareTo(key) < 0) {
            minKey = ceil(node.right, key, minKey);
        } else{
            minKey = node.key;
        }
        return minKey;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }
}
