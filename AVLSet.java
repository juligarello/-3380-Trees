import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AVLSet<T extends Comparable<? super T>> implements SortedSet<T> {

    protected NodeAVL<T> root;
    private int size;

    public AVLSet() {
        root = null;
        size = 0;
    }

    public int height() {
        return root.getHeight(root);
    }

    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        if(contains(key))
            return false;
        
        root = root.add(root, key);
        size++;

        return true;
    }

    public boolean remove(T key) {
        if (root == null)
            throw new NoSuchElementException();
        if(!contains(key))
            return false;
        
        root = root.remove(root, key);
        size--;

        return true;
    }

    public boolean contains(T key) {
        return root.get(root, key) != null;
    }

    public T min() {
        if (root == null)
            throw new NoSuchElementException();

        return root.minimum(root).key;
    }

    public T max() {
        if (root == null)
            throw new NoSuchElementException();

        return root.maximum(root).key;
    }

    public void removeMin() {
        if (root == null)
            throw new NoSuchElementException();

        root = root.removeMinimum(root);
        size--;
    }

    public void removeMax() {
        if (root == null)
            throw new NoSuchElementException();

        root = root.removeMaximum(root);
        size--;
    }

    public boolean repOk() {
        return root.isBST(root, null, null) && root.isAVL(root);
    }
    
    public String toString() {
        String result = "{";
        List<T> list = root.inOrder();

        for(int i = 0; i < list.size(); i++) {
            if(i != list.size() - 1) {
                result += list.get(i) + ", ";
            }   else    {
                result += list.get(i);
            }
        }
        result += "}";

        return result;         
    }
    
    public Iterator<T> iterator() {
        List<T> list = root.inOrder();

        return list.iterator();
    }
}
