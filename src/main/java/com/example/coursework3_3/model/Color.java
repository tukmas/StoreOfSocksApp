package com.example.coursework3_3.model;
/*
 * Цвет носков
 */
public enum Color {Black ("черный"), White ("белый"), Blue ("синий"), Grey ("серый"), Brown ("коричневый"), Ocher ("охра");
    String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
