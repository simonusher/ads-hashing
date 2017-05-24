package com.company;


public class Hash {
    Element[] tab;
    int size;
    private static final int _DEFAULT_ARRAY_SIZE = 13;

    public Hash(){
        this.tab = new Element[_DEFAULT_ARRAY_SIZE];
        this.size = 0;
    }

    public Hash(int size){
        this.tab = new Element[size];
        this.size = 0;
    }

    public Object get(String key){
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % tab.length;
        while(tab[h] != null && !tab[h].key.equals(key)){
            if(h == tab.length - 1) h = 0;
            else h++;
        }
        if(tab[h] == null) return null;
        else return tab[h];
    }

    public void put(String key, Object value){
        resize();
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % tab.length;
        while(tab[h] != null){
            if(h == tab.length - 1) h = 0;
            else h++;
        }
        tab[h] = new Element(key, value);
        size ++;
    }

    private void put(String key, Object value, Element[] tab){
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % tab.length;
        while(tab[h] != null){
            if(h == tab.length - 1) h = 0;
            else h++;
        }
        tab[h] = new Element(key, value);
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
            Element temp[] = new Element[2 * tab.length + 3];
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
    }
}
