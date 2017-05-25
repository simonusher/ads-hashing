package com.company;

/**
 * Created by Igor on 19.05.2017.
 */
public class DoubleHash {
    Element[] tab;
    int size;
    private int[] _DEFAULT_ARRAY_SIZES = {13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    public DoubleHash(){
        this.tab = new Element[_DEFAULT_ARRAY_SIZES[0]];
        this.size = 0;
    }

    public DoubleHash(int size){
        this.tab = new Element[size];
        this.size = 0;
    }

    public Object get(String key){
        int h1 = hashOne(key);
        int h2 = hashTwo(key);
        int hash = h1;
        int n = 0;
        while(tab[hash] != null && !tab[hash].key.equals(key)){
            n++;
            hash = (h1 + n * h2) % tab.length;
        }
        if(tab[hash] == null) return null;
        else return tab[hash];
    }

    public void put(String key, Object value){
        resize();
        int h1 = hashOne(key);
        int h2 = hashTwo(key);
        int hash = h1;
        int n = 0;
        while(tab[hash] != null){
            if(tab[hash].key.equals(key)){
                throw new DuplicateException();
            }
            n++;
            hash = (h1 + n * h2) % tab.length;
        }
        tab[hash] = new Element(key, value);
        size ++;
    }

    private void put(String key, Object value, Element[] tab){
        int h1 = hashOne(key, tab);
        int h2 = hashTwo(key, tab);
        int hash = h1;
        int n = 0;
        while(tab[hash] != null){
            if(tab[hash].key.equals(key)){
                throw new DuplicateException();
            }
            n++;
            hash = (h1 + n * h2) % tab.length;
        }
        tab[hash] = new Element(key, value);
    }

    public boolean containsKey(String key){
        return get(key) != null;
    }

    public int size(){
        int size = 0;
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] != null) size++;
        }
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    private void resize(){
        if((double)size/tab.length >= 0.75){
            int x = 0;
            for (int i = 0; i < _DEFAULT_ARRAY_SIZES.length; i++) {
                if(_DEFAULT_ARRAY_SIZES[i] > tab.length){
                    x = _DEFAULT_ARRAY_SIZES[i];
                    break;
                }
            }
            Element temp[] = new Element[x];
            for (int i = 0; i < tab.length; i++) {
                if(tab[i] != null){
                    put(tab[i].key, tab[i].value, temp);
                }
            }
            tab = temp;
        }
    }

    public void dump(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] == null) sb.append("null");
            else {
                sb.append(tab[i].key);
            }
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }

    private int hashOne(String key){
        return Math.abs(key.hashCode()) % tab.length;
    }

    private int hashOne(String key, Element[] tab){
        return Math.abs(key.hashCode()) % tab.length;
    }

    private int hashTwo(String key){
        char[] ch = key.toCharArray();
        int sum = 0;
        for (int i = 0; i < ch.length; i++) {
            sum += ch[i];
        }
        sum = sum % tab.length;
        if (sum == 0) sum = 1;
        return sum;
    }

    private int hashTwo(String key, Element[] tab){
        char[] ch = key.toCharArray();
        int sum = 0;
        for (int i = 0; i < ch.length; i++) {
            sum += ch[i];
        }
        sum = sum % tab.length;
        if (sum == 0) sum = 1;
        return sum;
    }

    private class Element{
        String key;
        Object value;

        public Element(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key;
        }
    }
}
