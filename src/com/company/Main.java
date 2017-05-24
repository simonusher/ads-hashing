package com.company;

public class Main {

    public static void main(String[] args) {
        DoubleHash h = new DoubleHash();
        h.put("Wozniak", "Szymon");
        h.put("Lesniara", "Kacper");
        h.dump();
        System.out.println(h.get("wozniaks"));
        System.out.println(h.containsKey("Wozniak"));
        System.out.println(h.containsKey("asfdasd"));
        h.put("Fronczkiewicz", "Alicja");
        h.put("Aniulis", "Jakub");
        h.put("Przydatek", "Maciej");
        h.put("Kowalski", "Jan");
        h.put("Skowroński", "Lukasz");
        h.put("Krystek", "Beata");
        h.put("Kowalenko", "Krystyna");
        h.put("Adamski", "Jakub");
        h.put("Gontarski", "Tomasz");
        h.put("Wilk", "Jakub");
        h.dump();
        System.out.println(h.containsKey("Wozniak"));
        System.out.println(h.containsKey("Fronczkiewicz"));
        System.out.println(h.containsKey("Aniulis"));
        System.out.println(h.containsKey("Przydatek"));
        System.out.println(h.containsKey("Kowalski"));
        System.out.println(h.containsKey("Skowroński"));
        System.out.println(h.containsKey("Krystek"));
        System.out.println(h.containsKey("Kowalenko"));
        System.out.println(h.containsKey("Adamski"));
    }
}
