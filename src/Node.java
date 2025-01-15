public class Node<K, V> {
    public K key;
    public V value;
    public Node<K,V> prev;
    public Node<K,V> next;

    public Node(final K key, final V value) {
        this.key = key;
        this.value = value;
    }
}
