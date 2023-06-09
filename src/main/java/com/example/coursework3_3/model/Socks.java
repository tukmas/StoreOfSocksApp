package com.example.coursework3_3.model;

import lombok.*;

@Getter
@EqualsAndHashCode
@Setter
@AllArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;



    public void setCottonPart(int cottonPart) {
        if (cottonPart >= 0 && cottonPart <= 100) {
            this.cottonPart = cottonPart;
        } else {
            System.out.println("Указано неверное значение состава. Укажите состав хлопка в % от 0 до 100.");
        }

    }

    @Override
    public String toString() {
        return "Носки: " +
                "цвет " + color +
                ", размер " + size +
                ", состав " + cottonPart +
                ". Количество на складе ";
    }
}