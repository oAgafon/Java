package com.example.java.firstLab;

public class ConsoleOutput {
    public static void main(String[] args) {
        var cont = new MyIntContainer();
        cont.append(1);
        cont.append(3);
        cont.add(2, 1);
        showCont(cont);
        for (int i = 0; i < 11; i++) {
            cont.append(6);
        }
        showCont(cont);
        cont.pop();
        cont.pop();
        cont.append(11);
        cont.remove(5);
        cont.remove(5);
        showCont(cont);
        System.out.println(cont.get(2));
    }

    private static void showCont(MyIntContainer cont) {
        System.out.println("Container: " + cont);
        System.out.println("Size of container: " + cont.size());
    }
}
