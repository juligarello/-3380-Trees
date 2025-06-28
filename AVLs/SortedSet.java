import java.util.NoSuchElementException;

/**
 * SortedSet es un conjunto ordenado sin límites de objetos de tipo T.
 * Un SortedSet típico es {o1, ..., on} con o1 < o2 < ... < on.
 *
 * Requiere que el tipo T implemente la interfaz Comparable,
 * lo que permite mantener los elementos ordenados naturalmente.
 *
 * Todas las comparaciones entre elementos usan el método compareTo.
 */
public interface SortedSet<T extends Comparable<? super T>> extends Iterable<T> {

    /**
     * Agrega el elemento 'x' al conjunto si no está presente.
     * 
     * @post this = old(this) ∪ {x}
     * @return true si el conjunto fue modificado, false si 'x' ya estaba presente.
     */
    public boolean add(T key);

    /**
     * Elimina el elemento 'x' del conjunto si está presente.
     * 
     * @post this = old(this) \ {x}
     * @return true si el conjunto fue modificado, false si 'x' no estaba presente.
     */
    public boolean remove(T key);

    /**
     * Devuelve la cantidad de elementos almacenados en el conjunto.
     * 
     * @post #this
     * @return el tamaño del conjunto.
     */
    public int size();

    /**
     * Verifica si el conjunto está vacío.
     *
     * @return true si el conjunto no contiene elementos (es decir, su tamaño es 0), false en caso contrario.
     */
public boolean isEmpty();

    /**
     * Verifica si el conjunto contiene el elemento dado.
     * 
     * @post Retorna true si y solo si 'key' ∈ this
     * @param key el elemento a buscar
     * @return true si 'key' está presente, false si no.
     */
    public boolean contains(T key);

    /**
     * Devuelve el menor elemento del conjunto.
     * 
     * @pre !isEmpty()
     * @return el mínimo elemento (según el orden natural).
     * @throws NoSuchElementException si el conjunto está vacío.
     */
    public T min();

    /**
     * Devuelve el mayor elemento del conjunto.
     * 
     * @pre !isEmpty()
     * @return el máximo elemento (según el orden natural).
     * @throws NoSuchElementException si el conjunto está vacío.
     */
    public T max();

    /**
     * Elimina el menor elemento del conjunto.
     * 
     * @pre !isEmpty()
     * @post El conjunto ya no contiene el menor elemento que tenía antes.
     * @throws NoSuchElementException si el conjunto está vacío.
     */
    public void removeMin();

    /**
     * Elimina el mayor elemento del conjunto.
     * 
     * @pre !isEmpty()
     * @post El conjunto ya no contiene el mayor elemento que tenía antes.
     * @throws NoSuchElementException si el conjunto está vacío.
     */
    public void removeMax();

    /**
     * Verifica el invariante de representación del conjunto.
     * 
     * Debe garantizar que:
     * - Se mantiene el orden de los elementos.
     * - No hay duplicados.
     * - No hay ciclos en la estructura de datos.
     * - La cantidad de nodos coincide con el tamaño.
     * 
     * @return true si el invariante se cumple, false si no.
     */
    public boolean repOk();

    /**
     * Devuelve una representación en cadena del conjunto.
     * 
     * @return una cadena con los elementos en orden ascendente. Ejemplo: [1, 2, 3, 4]
     */
    public String toString();
}

