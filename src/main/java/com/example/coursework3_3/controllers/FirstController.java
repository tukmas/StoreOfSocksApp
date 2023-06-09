package com.example.coursework3_3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/info")
    public String info() {
        return   "Курсовая работа №3: " +'\n' +
                "Создание веб-приложения для склада интернет-магазина носков." +'\n' +
                "Автор: Тукмачев Сергей";

    }
}
