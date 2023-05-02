public class MyHashTable <K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public int getM() {
        return M;
    }

    public int getSize() {
        return size;
    }

    public MyHashTable() {
        this.M = M;
        this.size = 0;
        this.chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        this.size = 0;
        this.chainArray = new HashNode[M];
    }

    private int hash(K key) {
        return key.hashCode() % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (chainArray[index] == null) {
            chainArray[index] = new HashNode<>(key, value);
        } else {
            HashNode<K, V> currentNode = chainArray[index];
            while (currentNode.next != null) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    return;
                }
                currentNode = currentNode.next;
            }
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
            } else {
                currentNode.next = new HashNode<>(key, value);
            }
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = chainArray[index];
        while (currentNode != null && !currentNode.key.equals(key)) {
            currentNode = currentNode.next;
        }
        return (currentNode == null) ? null : currentNode.value;
    }


    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = chainArray[index];
        HashNode<K, V> prevNode = null;
        while (currentNode != null && !currentNode.key.equals(key)) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        if (currentNode == null) {
            return null;
        }
        if (prevNode == null) {
            chainArray[index] = currentNode.next;
        } else {
            prevNode.next = currentNode.next;
        }
        size--;
        return currentNode.value;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> currentNode = chainArray[i];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> currentNode = chainArray[i];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return currentNode.key;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public int getBucketSize(int i){
        int size = 0;
        HashNode<K, V> cursor = chainArray[i];
        while (cursor != null) {
            size++;
            cursor = cursor.next;
        }
        return size;
    }


}
