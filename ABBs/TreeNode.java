package ABBs;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T extends Comparable<? super T>> implements BinaryTree<T> {

    protected T key;
    protected TreeNode<T> left;
    protected TreeNode<T> right;

    public TreeNode(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public T getRoot() {
        return key;
    }

    public TreeNode<T> add(TreeNode<T> x, T data) {
        if(x == null)
            return new TreeNode<>(data);

        int cmp = x.key.compareTo(data);
        if(cmp < 0) {
            x.right = add(x.right, data);
        } else if(cmp > 0) {
            x.left = add(x.left, data);
        }
        return x;
    }

    public TreeNode<T> remove(TreeNode<T> x, T data) {
        int cmp = x.key.compareTo(data);

        if(cmp < 0) {
            x.right = remove(x.right, data);
        } else if(cmp > 0) {
            x.left = remove(x.left, data);
        }   else    {
            if(x.left == null) {
                return x.right;
            }
            if(x.right == null) {
                return x.left;
            }
            TreeNode<T> aux = x;
            x = minimum(aux.right);
            x.right = removeMinimum(aux.right);
            x.left = aux.left;
        }

        return x;
    }

    public TreeNode<T> get(TreeNode<T> x, T data) {
        if(x == null)
            return null;
        
        int cmp = x.key.compareTo(data);
        if(cmp < 0) {
            return get(x.right, data);
        } else if(cmp > 0) {
            return get(x.left, data);
        }   else    {
            return x;
        }
    }

    public void setRoot(T item) {
        key = item;
    }

    public boolean isEmpty() {
        return key == null;
    }

    public void makeEmpty() {
        key = null;
        left = null;
        right = null;
    }

    public TreeNode<T> minimum(TreeNode<T> x) {
        if(x.left == null)
            return x;
        
        return minimum(x.left);
    }

    public TreeNode<T> maximum(TreeNode<T> x) {
        if(x.right == null)
            return x;
        
        return maximum(x.left);
    }

    public TreeNode<T> removeMinimum(TreeNode<T> x) {
        if(x.left == null)
            return x.right;

        left = removeMinimum(x.left);
        return x;
    }

    public TreeNode<T> removeMaximum(TreeNode<T> x) {
        if(x.right == null)
            return x.left;

        right = removeMaximum(x.left);
        return x;
    }

    public boolean isBST(TreeNode<T> x, T min, T max) {
        if(x == null)
            return true;
        if(min != null && x.key.compareTo(min) <= 0)
            return false;
        if(max != null && x.key.compareTo(max) >= 0)
            return false;

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }
    
    public List<T> preOrder() {
        List<T> preorder = new LinkedList<>();

        preorder.add(key);
        if(left != null) {
            preorder.addAll(left.preOrder());
        }
        if(right != null) {
            preorder.addAll(right.preOrder());
        }

        return preorder;
    }

    public List<T> inOrder() {
        List<T> inorder = new LinkedList<>();

        if(left != null) {
            inorder.addAll(left.inOrder());
        }
        inorder.add(key);
        if(right != null) {
            inorder.addAll(right.inOrder());
        }

        return inorder;
    }

    public List<T> postOrder() {
        List<T> postorder = new LinkedList<>();

        if(left != null) {
            postorder.addAll(left.postOrder());
        }
        if(right != null) {
            postorder.addAll(right.postOrder());
        }
        postorder.add(key);

        return postorder;
    }

}
