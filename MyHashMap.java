class MyHashMap<K, V> {
    /* Inner class to represent an entry in the hash map */
    private static class Entry<K, V> {
        K key;          /* Key of the entry */
        V value;        /* Value associated with the key */
        Entry<K, V> next; /* Reference to the next entry in the chain (for handling collisions) */

        /* Constructor to initialize an entry with key and value */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Array to hold the entries, representing the table of the hash map */
    private Entry<K, V>[] table;
    /* Number of key-value pairs in the hash map */
    private int size;

    /* Constructor to initialize the hash map with a default capacity */
    public MyHashMap() {
        table = new Entry[16]; /* Default capacity of the table */
        size = 0;
    }

    /* Method to add or update a key-value pair in the hash map */
    public void put(K key, V value) {
        /* Calculate the index in the table based on the hash code of the key */
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];

        /* If no entry exists at the index, create a new entry */
        if (entry == null) {
            table[index] = new Entry<>(key, value);
            size++;
            return;
        }

        /* Traverse the chain to find the correct position to insert the new entry or update an existing one */
        while (true) {
            /* If the key already exists, update its value */
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            /* If the end of the chain is reached, add the new entry */
            if (entry.next == null) {
                entry.next = new Entry<>(key, value);
                size++;
                return;
            }
            entry = entry.next;
        }
    }

    /* Method to retrieve the value associated with a given key */
    public V get(K key) {
        /* Calculate the index in the table based on the hash code of the key */
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];

        /* Traverse the chain to find the entry with the matching key */
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value; /* Return the associated value */
            }
            entry = entry.next;
        }
        return null; /* Return null if the key is not found */
    }

    /* Method to remove a key-value pair from the hash map */
    public void remove(K key) {
        /* Calculate the index in the table based on the hash code of the key */
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];
        Entry<K, V> prev = null;

        /* Traverse the chain to find the entry with the matching key */
        while (entry != null) {
            if (entry.key.equals(key)) {
                /* If the entry to remove is the first one in the chain */
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return;
            }
            prev = entry;
            entry = entry.next;
        }
    }

    /* Method to get the number of key-value pairs in the hash map */
    public int size() {
        return size;
    }

    /* Method to calculate the index in the table based on the hash code of the key */
    private int indexFor(int hashCode) {
        return Math.abs(hashCode) % table.length;
    }

    /* Method to return a list of all keys in the hash map */
    public MyArrayList<K> keySet() {
        MyArrayList<K> keys = new MyArrayList<>();
        /* Iterate through the table and all chains to collect keys */
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }
}
