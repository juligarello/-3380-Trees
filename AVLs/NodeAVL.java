import java.util.LinkedList;
import java.util.List;

public class NodeAVL<T extends Comparable<? super T>> implements BinaryTree<T> {

    protected T key;
    protected int height;
    protected NodeAVL<T> left;
    protected NodeAVL<T> right;

    public NodeAVL(T key) {
        this.key = key;
        this.height = 0;
        this.left = null;
        this.right = null;
    }

    public T getRoot() {
        return key;
    }

    public void setRoot(T item) {
        key = item;
    }

    public int getHeight(NodeAVL<T> x) {
        if(x == null)
            return 0;

        return height;
    }

    public int balanceFactor(NodeAVL<T> x) {
        return getHeight(x.left) - getHeight(x.right);
    }

    public NodeAVL<T> rotateLeft(NodeAVL<T> x) {
        NodeAVL<T> y = x.right;
        x.right = y.left;
        y.left = x;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return y;
    }

    public NodeAVL<T> rotateRight(NodeAVL<T> x) {
        NodeAVL<T> y = x.left;
        x.left = y.right;
        y.right = x;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        
        return y;
    }

    public NodeAVL<T> balance(NodeAVL<T> x) {
        if(balanceFactor(x) < -1) {
            if(balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if(balanceFactor(x) > 1) {
            if(balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }

        return x;
    }

    public boolean isEmpty() {
        return key == null;
    }

    public void makeEmpty() {
        key = null;
        left = null;
        right = null;
    }
    
    public NodeAVL<T> add(NodeAVL<T> x, T data) {
        if(x == null)
            return new NodeAVL<>(data);

        int cmp = x.key.compareTo(data);
        if(cmp < 0) {
            x.right = add(x.right, data);
        } else if(cmp > 0) {
            x.left = add(x.left, data);
        }

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return balance(x);
    }

    public NodeAVL<T> remove(NodeAVL<T> x, T data) {
        int cmp = x.key.compareTo(data);
        if(cmp < 0) {
            x.right = remove(x.right, data);
        } else if(cmp > 0) {
            x.left = remove(x.left, data);
        }   else    {
            if(x.left == null)
                return x.right;
            if(x.right == null)
                return x.left;

            NodeAVL<T> aux = x;
            x = minimum(aux.right);
            x.right = removeMinimum(aux.right);
            x.left = aux.left;
        }

        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return balance(x);
    }

    public NodeAVL<T> get(NodeAVL<T> x, T data) {
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

    public NodeAVL<T> minimum(NodeAVL<T> x) {
        if(x.left == null)
            return x;
        
        return minimum(x.left);
    }

    public NodeAVL<T> maximum(NodeAVL<T> x) {
        if(x.right == null)
            return x;
        
        return maximum(x.left);
    }

    public NodeAVL<T> removeMinimum(NodeAVL<T> x) {
        if(x.left == null)
            return x.right;

        left = removeMinimum(x.left);
        return x;
    }

    public NodeAVL<T> removeMaximum(NodeAVL<T> x) {
        if(x.right == null)
            return x.left;

        right = removeMaximum(x.left);
        return x;
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

    public boolean isBST(NodeAVL<T> x, T min, T max) {
        if(x == null)
            return true;

        if(min != null && min.compareTo(x.key) >= 0)
            return false;
        if(max != null && max.compareTo(x.key) <= 0)
            return false;

        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    public boolean isAVL(NodeAVL<T> x) {
        if(x == null)
            return true;

        if(balanceFactor(x) > 1 || balanceFactor(x) < -1)
            return false;

        return isAVL(x.left) && isAVL(x.right);
    }   
}
