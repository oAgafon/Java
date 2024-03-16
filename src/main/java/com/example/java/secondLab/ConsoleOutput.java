package com.example.java.secondLab;

public class ConsoleOutput {
    public static void main(String[] args) {
        var con = new Converter("   (0-  ((5^3)/(5  +x)+2* y-10) )");
        System.out.println(con.compute());
    }
}
