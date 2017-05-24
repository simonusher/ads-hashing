package com.company;

import java.util.LinkedList;

/**
 * Created by Igor on 24.05.2017.
 */
public class ArrayOfLists {
    LinkedList<Element>[] array;
    private int[] _DEFAULT_ARRAY_SIZES = {13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private int size = 0;

    public ArrayOfLists() {
        array = new LinkedList[_DEFAULT_ARRAY_SIZES[0]];
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList<>();
        }
    }

    public Object get(String key){
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % array.length;
        for (Element e : array[h]) {
            if (e.key.equals(key)) return e.value;
        }
        return null;
    }

    public void put(String key, Object value){
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % array.length;
        if(array[h].contains(value)) throw new DuplicateException();
        else {
            array[h].add(new Element(key, value));
            size++;
        }
    }

    public void put(String key, Object value, LinkedList[] temp){
        int h = key.hashCode();
        if (h < 0) h *= -1;
        h = h % temp.length;
        if(temp[h].contains(value)) throw new DuplicateException();
        else {
            temp[h].add(new Element(key, value));
        }
    }

    public boolean containsKey(String key){
        return get(key) != null;
    }

    public int size(){
        return size;
    }

    public void resize(){
        if(size > 2 * array.length){
            int x = 0;
            for (int i = 0; i < _DEFAULT_ARRAY_SIZES.length; i++) {
                if(_DEFAULT_ARRAY_SIZES[i] > array.length){
                    x = _DEFAULT_ARRAY_SIZES[i];
                    break;
                }
            }
            LinkedList[] temp = new LinkedList[x];
            for (int i = 0; i < array.length; i++) {
                for (Element e : array[i]) {
                    put(e.key, e.value, temp);
                }
            }
            array = temp;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void dump(){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
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
