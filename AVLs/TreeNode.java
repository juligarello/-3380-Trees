import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> implements BinaryTree<T> {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode() {
        data = null;
        left = null;
        right = null;
    }

    public Object getRoot() {
        return data;
    }

    public void setRoot(T item) {
        data = item;
    }

    public boolean isEmpty() {
        return data == null;
    }

    public void makeEmpty() {
        data = null;
        left = null;
        right = null;
    }
    
    public List<T> preOrder() {
        List<T> preorder = new LinkedList<>();

        preorder.add(data);
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
        inorder.add(data);
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
        postorder.add(data);

        return postorder;
    }

}
