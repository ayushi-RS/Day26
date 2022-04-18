class LFUCache {

    HashMap<Integer, Integer> keyValue; //key and value
    HashMap<Integer, Integer> counter; // key and counter
    HashMap<Integer, LinkedHashSet<Integer>> counterAndItems; //counter and items list
    private static int capacity, min;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyValue  = new HashMap<>();
        counter = new HashMap<>();
        counterAndItems = new HashMap<>();
        this.min = -1;
    }
    
    public int get(int key) {
        if(!keyValue.containsKey(key))
            return -1;
        int value = keyValue.get(key);
        int count = counter.get(key);
        counter.put(key, count+1);
        counterAndItems.get(count).remove(key);
        
        if(min==count && counterAndItems.get(count).size()==0){
            min++;
        }
        if(!counterAndItems.containsKey(count+1)){
            counterAndItems.put(count+1, new LinkedHashSet<>());
        }
        counterAndItems.get(count+1).add(key);
        return value;
    }
    
    public void put(int key, int value) {
        if(capacity<=0)
            return;
        if(keyValue.containsKey(key)){
            keyValue.put(key, value);
            get(key);
            return;
        }
        else {
            if(keyValue.size()>=capacity){
                int evict = counterAndItems.get(min).iterator().next();
                keyValue.remove(evict);
                counter.remove(evict);
                counterAndItems.get(min).remove(evict);
            }
            keyValue.put(key, value);
            counter.put(key, 1);
            min = 1;
            if(!counterAndItems.containsKey(min))
                counterAndItems.put(min, new LinkedHashSet<>());
            counterAndItems.get(min).add(key);
        }
    }
}