import java.util.LinkedList;
import java.util.List;

public class NodeBST<T extends Comparable<? super T>> implements BinaryTree<T> {

    protected T key;
    protected NodeBST<T> left;
    protected NodeBST<T> right;

    public NodeBST(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public T getRoot() {
        return key;
    }

    public NodeBST<T> add(NodeBST<T> x, T data) {
        if(x == null)
            return new NodeBST<>(data);

        int cmp = x.key.compareTo(data);
        if(cmp < 0) {
            x.right = add(x.right, data);
        } else if(cmp > 0) {
            x.left = add(x.left, data);
        }
        return x;
    }

    public NodeBST<T> remove(NodeBST<T> x, T data) {
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
            NodeBST<T> aux = x;
            x = minimum(aux.right);
            x.right = removeMinimum(aux.right);
            x.left = aux.left;
        }

        return x;
    }

    public NodeBST<T> get(NodeBST<T> x, T data) {
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

    public NodeBST<T> minimum(NodeBST<T> x) {
        if(x.left == null)
            return x;
        
        return minimum(x.left);
    }

    public NodeBST<T> maximum(NodeBST<T> x) {
        if(x.right == null)
            return x;
        
        return maximum(x.left);
    }

    public NodeBST<T> removeMinimum(NodeBST<T> x) {
        if(x.left == null)
            return x.right;

        left = removeMinimum(x.left);
        return x;
    }

    public NodeBST<T> removeMaximum(NodeBST<T> x) {
        if(x.right == null)
            return x.left;

        right = removeMaximum(x.left);
        return x;
    }

    public boolean isBST(NodeBST<T> x, T min, T max) {
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
