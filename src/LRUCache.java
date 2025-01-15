import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache<K, V> {
    private final Map<K, Node<K,V>> cacheMap;
    private final Node<K,V> root;
    private final Node<K,V> last;
    private int capacity;

    public LRUCache() {
        cacheMap = new ConcurrentHashMap<>();
        root = new Node<>(null, null);
        last = new Node<>(null, null);
        root.next = last;
        last.prev = root;
        capacity = 5;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    public V getKey(final K key) {
        if (!cacheMap.containsKey(key)) {
           return null;
        }

        Node<K,V> node = cacheMap.get(key);
        synchronized(this) {
            removeNode(node);
            addNodeAtFront(node);
        }
        return node.value;
    }

    public synchronized void putKey(final K key, final V value) {
        if (cacheMap.containsKey(key)) {
            removeNode(cacheMap.get(key));
        } else if (capacity==cacheMap.size()) {
            removeLastNode();
        }

        Node<K,V> node = new Node<>(key, value);
        cacheMap.put(key, node);
        addNodeAtFront(node);
    }

    private void removeNode(final Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeAtFront(final Node<K,V> node) {
        node.next = root.next;
        node.prev = root;
        root.next = node;
        node.next.prev = node;
    }

    private void removeLastNode() {
        Node<K,V> node = last.prev;
        System.out.println("Removing key " + node.key);
        if (node==root) {
            return;
        }

        node.prev.next = last;
        last.prev = node.prev;
        cacheMap.remove(node.key);
    }
}
