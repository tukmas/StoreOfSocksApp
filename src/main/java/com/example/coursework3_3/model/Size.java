package com.example.coursework3_3.model;
/*
 * Размер носков
 */
public enum Size {
    XS(30), S(34),
    M(38), L(42), XL(45);
    private int size;
    Size(int size) {
        this.size=size;
    }
}
