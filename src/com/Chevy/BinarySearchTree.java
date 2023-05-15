package com.Chevy;

import java.util.Locale;

/**
 * The class BinarySearchTree implements a binary search tree. The type of each
 * element consists of two components: word and meaning. The organization of the
 * tree is based on the word component.
 *
 * @author Josias
 */
public class BinarySearchTree
{

    private Node root;    //root of the bst; implemented as a dummy node.

    /**
     * Default constructor. Sets this object as an empty BST.
     */
    public BinarySearchTree()
    {
        root = new Node();        //dummy node as the root
        root.setLeftChild(null);
        root.setRightChild(null);
        root.setInfo(new ElementType("DUMMY word", "DUMMY meaning"));
    }

    /**
     * Adds a new element to the BST.
     *
     * @param x new element
     */
    //Inspired by prog22_01 we are checking if the node is open. if not we are calling the recursive private add function. In the recursive function we do a similar check but also for the right child.
    public void add(ElementType x)
    {
        if (root.getLeftChild() == null){
            Node p = new Node();
            p.setNode(x, null, null);
            root.setLeftChild(p);
        } else
            add(x, root.getLeftChild());
    }

    /**
     * Determines if given word is in the BST.
     *
     * @param x given word
     * @return true if x is a word in the tree, false otherwise
     *
     */
    //Inspired from prog22_01. Used the private method contain to find if the specified word is in the BST. We use here root.getLeftChild as a starting point. Lower case for the cmp method
    public boolean contain(String x)
    {
        return contain(x.toLowerCase(), root.getLeftChild());
    }

    /**
     * If given word is found in the tree, meaning of word is returned;
     * otherwise, empty string is returned.
     *
     * @param x given word
     * @return meaning of given word if word is in the tree; empty string
     * otherwise
     */
    //this one was inspired by the contain method above. Only difference is that we return a String instead of Boolean.
    public String find(String x)
    {
        return find(x.toLowerCase(), root.getLeftChild());
    }

    /**
     * Returns number of elements in the BST.
     *
     * @return number of elements in the BST
     */
    //Inspired by prog22_01 we add the up the return from the recursive getcount1 function to a total.
    public int getCount(Node p)
    {
        return getCount1(root.getLeftChild()) + getCount1(root.getRightChild());
    }

    /**
     * Determines if this BST is empty.
     *
     * @return true if tree is empty, false otherwise
     */
    //prog22_01 inspired. If the root (the first node) has a null leftchild then BST is empty
    public boolean isEmpty()
    {
        return root.getLeftChild() == null;
    }

    /**
     * If given word is found in the tree, it replaces its meaning with the
     * given one and a value of true is returned; otherwise false is returned.
     *
     * @param x given word
     * @param y given meaning
     * @return true if replacement is successful, false otherwise
     */
    //I drew inspiration from the find/contain methods. We return boolean here if there is a word's meaning we can replace. We also replace the meaning if true.
    public boolean replace(String x, String y)
    {
        return replace(x, y, root.getLeftChild());
    }

    /**
     * Builds a String description of this BST.
     *
     * @return String containing the BST elements
     */
    //Borrowing the already defined toString private method to call it publicly for later use in the Dictionary class.
    public String toString()
    {
        return toString(root.getLeftChild());
    }

    /**
     * Recursive implementation of method add(ElementType x).
     */
    //inspired from prog22_01 insert recursive method. We compare x and the info of Node p. If it's less than 0 (-1), we add to the left node. Else we add to right node.
    private void add(ElementType x, Node p)
    {
        if (cmp(x.word, p) < 0)

            if (p.getLeftChild() == null)
            {
                Node q = new Node();
                q.setNode(x, null, null);
                p.setLeftChild(q);
            } else
                add(x, p.getLeftChild());

        else if (p.getRightChild() == null)
        {
            Node q = new Node();
            q.setNode(x, null, null);
            p.setRightChild(q);
        } else
            add(x, p.getRightChild());

    }


    /**
     * Compares given string with word in given node.
     *
     * @param x given string
     * @param p given node
     * @return -1 if x is less than word in node, 0 if x is equal to word in
     * node, 1 if x is greater than word in node
     */
    private int cmp(String x, Node p)
    {
        if (x.compareTo(p.getInfo().word) < 0)
            return -1;
        else
        if (x.compareTo(p.getInfo().word) == 0)
            return 0;
        else
            return 1;
    }

    /**
     * Recursive implementation of method contain(String x).
     */
    //used prog22_01 for inspiration on implementing this method. if the null is empty we return false. If x matches the info of Node P we return true. Else if we check recursively into the children.
    private boolean contain(String x, Node p)
    {
        if (p == null)
            return false;
        else if (cmp(x, p) == 0)
            return true;
        else if (cmp(x, p) < 0)
            return contain(x, p.getLeftChild());
        else
            return contain(x, p.getRightChild());
    }

    /**
     * Recursive implementation of method find(String x).
     */
    //Same as the contain method above, but we return strings instead of booleans. We return meaning of the word after we traverse the tree recursively.
    private String find(String x, Node p)
    {
        if (p == null)
            return "";

        else if (cmp(x, p) == 0)
            return p.getInfo().meaning;

        else if (cmp(x, p) < 0)
            return find(x, p.getLeftChild());

        else
            return find(x, p.getRightChild());
    }

    /**
     * Recursive implementation of method getCount().
     */
    //Got it from prog22_01. if it's empty we return 0 to the total. If the node isn't empty we recurse both children while adding to the total.
    private int getCount1(Node p)
    {
        if (p == null)
            return 0;
        else
            return 1 + getCount1(p.getLeftChild()) + getCount1(p.getRightChild());
    }

    /**
     * Recursive implementation of method replace(String x, String y).
     */
    //Similar to the contain method. The only difference is that when true we change the meaning string of the found word.
    private boolean replace(String x, String y, Node p)
    {
        if (p == null)
            return false;

        else if (cmp(x, p) == 0)
        {p.getInfo().meaning = y;
            return true;}

        else if (cmp(x, p) < 0)
            return replace(x, y, p.getLeftChild());

        else
            return replace(x, y, p.getRightChild());
    }

    /**
     * Recursive implementation of method toString().
     */
    private String toString(Node p)
    {
        if (p != null)
            return toString(p.getLeftChild())
                    + p.getInfo().word + ": " + p.getInfo().meaning + "\n"
                    + toString(p.getRightChild());
        else
            return "";
    }

}
