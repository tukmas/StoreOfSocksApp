package com.example.coursework3_3.model;
/*
 * Размер носков
 */
public enum Size {S ("S"), M ("M"), L ("L"), XL ("XL"), XXL ("XXL"), XXXL ("XXXL");
    String size;

    Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }
}
