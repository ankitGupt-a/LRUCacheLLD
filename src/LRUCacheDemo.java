public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<String,Integer> lruCache = new LRUCache<>();

        lruCache.putKey("Key1", 1);
        lruCache.putKey("Key2", 2);
        lruCache.putKey("Key3", 3);
        lruCache.putKey("Key4", 4);
        lruCache.putKey("Key5", 5);
        System.out.println(lruCache.getKey("Key1"));
        System.out.println(lruCache.getKey("Key2"));
        System.out.println(lruCache.getKey("Key3"));
        System.out.println(lruCache.getKey("Key4"));
        System.out.println(lruCache.getKey("Key5"));

        lruCache.putKey("Key6", 6);
        System.out.println(lruCache.getKey("Key1"));
        System.out.println(lruCache.getKey("Key2"));  // output null
        lruCache.putKey("Key7", 7);
        System.out.println(lruCache.getKey("Key3")); // output null
    }
}
