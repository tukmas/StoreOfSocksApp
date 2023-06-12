package com.example.coursework3_3.model;

public class Sock {
    private int cottonPart;
    private int quantity;
    private Size size;
    private Color color;
    public Sock(int cottonPart, int quantity, Size size, Color color) {
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public Size getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return
                "\nсодержание хлопка - " + cottonPart +
                        "%, \n" +
                        "размер - " + size +
                        "\nцвет - " + color +
                        "\nколичество - "+ quantity + "шт.\n";
    }
}
