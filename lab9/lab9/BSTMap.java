package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 * Now I should start
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = p.key.compareTo(key); // built-in Comparable method, just use compareTo() method
        if (cmp == 0) {
            return p.value; // find out the correct key if equal
        } else if (cmp > 0) {   // bigger one should be in right node
            if (p.right != null) {  // keep tracking if this node has right node
                return getHelper(key, p.right);
            }
            return null;    // otherwise, the key is not saved in this tree, no correct value
        } else {    // smaller one should be in left node
            if (p.left != null) {   // same as right node
                return getHelper(key, p.left);
            }
            return null;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("arguments to get() is null.");
        }   // key should be valid
        return getHelper(key, root);    // find the key from root.
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {// insert a new Node if p is null
            if (p == root) {
                root = new Node(key, value);
            }
            p = new Node(key, value);
            return p;  // return the new node
        }
        int cmp = p.key.compareTo(key);
        if (cmp == 0) { // update the value to the node
            p.value = value;
            return p;   // return the new node
        } else if (cmp > 0) {   // track to the right node
            return putHelper(key, value, p.right);  // if the node is bigger than node's key
        } else {    // track to the left node
            return putHelper(key, value, p.left);   // if the node is smaller than node's key.
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null!");
        }   // key should be valid
        if (value == null) {
            throw new IllegalArgumentException("value is null!");
        }   // value should be valid
        putHelper(key, value, root);    // call the function where the node starts from root
        size++; // size changes
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
