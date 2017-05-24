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
        int h1 = key.hashCode();
        if (h1 < 0) h1 *= -1;
        h1 = h1 % tab.length;
        int h2 = key.charAt(0) % tab.length;
        if(h2 == 0) h2 = 1;
        int n = 0;
        while(tab[h1] != null && !tab[h1].key.equals(key)){
            n++;
            h1 += h2 * n;
            while(h1 >= tab.length -1){
                h1 -= (tab.length - 1);
            }
        }
        if(tab[h1] == null) return null;
        else return tab[h1];
    }

    public void put(String key, Object value){
        resize();
        int h1 = key.hashCode();
        if (h1 < 0) h1 *= -1;
        h1 = h1 % tab.length;
        int h2 = key.charAt(0) % tab.length;
        if(h2 == 0) h2 = 1;
        int n = 0;
        while(tab[h1] != null){
            n++;
            h1 += h2 * n;
            while(h1 >= tab.length -1){
                h1 -= (tab.length - 1);
            }
        }
        tab[h1] = new Element(key, value);
        size ++;
    }

    private void put(String key, Object value, Element[] tab){
        int h1 = key.hashCode();
        if (h1 < 0) h1 *= -1;
        h1 = h1 % tab.length;
        int h2 = key.charAt(0) % tab.length;
        if(h2 == 0) h2 = 1;
        int n = 0;
        while(tab[h1] != null){
            n++;
            h1 += h2 * n;
            while(h1 >= tab.length -1){
                h1 -= (tab.length - 1);
            }
        }
        tab[h1] = new Element(key, value);
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
