package com.example.java.firstLab;

/**
 * Класс контейнер, позволяющий хранить произвольное количество целых чисел
 * @author o_agafon
 * @version 1.0
 * @since 1.0
 */
public class MyIntContainer {

    /** Константа, отвечающая за начальный размер хранилища*/
    private static final int DEFAULT_CAPACITY = 10;

    /** Массив, отвечающий за хранение элемнтов контейнера*/
    private int[] elements;

    /** Поле, хранящее текущий размер массива elements*/
    private int capacity;

    /** Поле, храниящее количество ячеек, занятых числами*/
    private int size;

    /**
     * Конструктор класса инициализирующий базовый размер хранилища
     * количество занятых ячеек и само хранилище
     * без параметров
     */
    public MyIntContainer() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        elements = new int[capacity];
    }


    /**
     * Метод, который удаляет последний элемент
     */
    public void pop() {
        remove(size-1);
    }


    /**
     * Метод, который добавляет элемент в конец
     */
    public void append(int elem) {
        add(elem, size);
    }


    /**
     * Метод, который возвращает элемент по
     * @param index заданному индексу
     */
    public int get(int index) {
        if (isInRange(index)) {
            return elements[index];
        }
        else {
            throw new IndexOutOfBoundsException("Вы вышли за пределы контейнера.");
        }
    }


    /**
     * Метод, который удаляет элемент по
     * @param index заданному индексу, оставляя 0 на пустых местах
     */
    public void remove(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[size - 1] = 0;

        size--;
    }


    /**
     * Метод, который добавляет
     * @param elem заданный элемент
     * @param index по заданному индексу
     */
    public void add(int elem, int index) {
        if (isInRange(index)) {
            updateCapacityIfNecessary();

            insertAndSchedule(elem, index);
        } else {
            throw new IndexOutOfBoundsException("Вы вышли за пределы контейнера.");
        }
    }


    /**
     * Метод, который возвращает фактический размер хранилища
     */
    public int size() {
        return size;
    }


    /**
     * Метод переопределяющий метод toString от класса Object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * Метод, проверяющий, входит ли индекс в границы хранилища
     * @param index индекс для проверки
     */
    private boolean isInRange(int index) {
        return index >= 0 && index <= size + 1;
    }


    /**
     * Метод пересоздающий хранилище с уеличенным размером,
     * с сохранением элементов и их индексов
     * без параметров
     */
    private void reInit() {
        capacity *= 2 ;

        int[] oldElements = elements;
        elements = new int[capacity];

        if (size >= 0) {
            System.arraycopy(oldElements, 0, elements, 0, size);
        }
    }


    /** Метод, который проверяет текущий размер хранилища
     * и запускает метод reInit() для увеличения размера хранилища
     * */
    private void updateCapacityIfNecessary() {
        if (capacity >= size * 0.8) {
            reInit();
        }
    }


    /** Метод, который вставляет новый элемент по заданному индексу
     * и сдвигает старые элементы вправо
     * */
    private void insertAndSchedule(int elem, int index) {
        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = elem;
        size++;
    }
}
