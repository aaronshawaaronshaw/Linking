package edu.rice.comp322;

import static edu.rice.hj.Module1.*;
import static edu.rice.hj.Module0.doWork;

import edu.rice.hj.api.HjFuture;
import edu.rice.hj.api.SuspendableException;

/**
 * Object defining the node of a binary tree. The tree itself is built as a
 * series of nodes hooked up together.
 */
public class TreeNode {
    /**
     * Left child.
     */
    private final TreeNode left;

    /**
     * Right child.
     */
    private final TreeNode right;

    /**
     * Value at this node.
     */
    private int item;

    /**
     * Constructor for a leaf node.
     * @param item - the value stored at this node
     */
    public TreeNode(int item) {
        this.item = item;
        this.left = null;
        this.right = null;
    }

    /**
     * Alternate constructor for a node, constructs a non-leaf node.
     * @param left - the left child, a TreeNode
     * @param right - the right child, a TreeNode
     * @param item - the value stored at this node
     */
    public TreeNode(TreeNode left, TreeNode right, int item) {
        this.left = left;
        this.right = right;
        this.item = item;
    }

    /**
     * Returns the value of the node for leaves, or the value of the node plus
     * the value of the left child minus the value of the right child, checks
     * that the tree was built correctly.
     *
     * <p>TODO Modify itemCheckHelper to execute its child itemCheckHelper calls
     * asynchronously using future(). HINT: You may need to change the return
     * type of chooseHelper, which is fine because this is a private method.
     * This may also require small changes to itemCheck().</p>
     *
     * @return - the check value of the node
     */
    private Integer itemCheckHelper() throws SuspendableException {
        if (left == null) {
            assert (right == null);
            doWork(1);
            return item;
        } else {
            HjFuture<Integer> rightTree = future(() -> right.itemCheckHelper());
            HjFuture<Integer> leftTree = future(() -> left.itemCheckHelper());
            doWork(1);

            return item + leftTree.get() - rightTree.get();
        }
    }

    /**
     * Main entrypoint for checking the correctness of a generated tree.
     *
     * @return - the check value of the node
     */
    public int itemCheck() throws SuspendableException {
        return itemCheckHelper();
    }
}
