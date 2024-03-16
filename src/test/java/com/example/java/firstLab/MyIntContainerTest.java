package com.example.java.firstLab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyIntContainerTest {

    private MyIntContainer cont;

    @BeforeEach
    void setup() {
        cont = new MyIntContainer();
    }

    @Test
    public void pop_positive() {
        cont.append(5);
        cont.pop();

        assertEquals(0, cont.size());
    }

    @Test
    public void pop_negative() {
        assertThrows(IndexOutOfBoundsException.class, () -> cont.pop());
    }

    @Test
    public void append_positive() {
        cont.append(5);
        cont.append(3);

        assertEquals(3, cont.get(cont.size()-1));
    }

    @Test
    public void append_negative() {
        cont.append(5);
        cont.append(3);

        assertNotEquals(5, cont.get(cont.size()-1));
    }

    @Test
    public void get_positive() {
        cont.append(10);

        assertEquals(10, cont.get(0));
    }

    @Test
    public void get_negative() {
        assertThrows(IndexOutOfBoundsException.class, () -> cont.get(5));
    }

    @Test
    public void remove_positive() {
        cont.append(1);
        cont.append(2);
        cont.append(3);
        cont.remove(1);

        assertEquals(2, cont.size());
        assertEquals(3, cont.get(1));
    }

    @Test
    public void remove_negative() {
        assertThrows(IndexOutOfBoundsException.class, () -> cont.remove(4));
    }

    @Test
    public void add_positive() {
        cont.append(1);
        cont.append(3);
        cont.add(10, 1);

        assertEquals(3, cont.size());
        assertEquals(10, cont.get(1));
    }

    @Test
    public void add_negative() {
        assertThrows(IndexOutOfBoundsException.class, () -> cont.add(10, 5));
    }

    @Test
    public void size_positive() {
        for (int i = 0; i < 10; i++) {
            cont.append(i);
        }

        assertEquals(10, cont.size());
    }

    @Test
    public void size_negative() {
        cont.append(5);
        cont.pop();

        assertNotEquals(1, cont.size());
    }
}
