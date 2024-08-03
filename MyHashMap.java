class MyHashMap<K, V> {
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] table;
    private int size;

    public MyHashMap() {
        table = new Entry[16];
        size = 0;
    }

    public void put(K key, V value) {
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key, value);
            size++;
            return;
        }

        while (true) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            if (entry.next == null) {
                entry.next = new Entry<>(key, value);
                size++;
                return;
            }
            entry = entry.next;
        }
    }

    public V get(K key) {
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = indexFor(key.hashCode());
        Entry<K, V> entry = table[index];
        Entry<K, V> prev = null;

        while (entry != null) {
            if (entry.key.equals(key)) {
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

    public int size() {
        return size;
    }

    private int indexFor(int hashCode) {
        return Math.abs(hashCode) % table.length;
    }

    public MyArrayList<K> keySet() {
        MyArrayList<K> keys = new MyArrayList<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }
}
