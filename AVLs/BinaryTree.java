import java.util.List;

public interface BinaryTree<T> {

    public T getRoot();

    public void setRoot(T item);

    public boolean isEmpty();

    public void makeEmpty();

    public List<T> preOrder();

    public List<T> postOrder();

    public List<T> inOrder();

}
