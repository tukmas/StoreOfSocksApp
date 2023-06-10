package com.example.coursework3_3.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sock {
    private int cottonPart;
    private int quantity;
    private Size size;
    private Color color;

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