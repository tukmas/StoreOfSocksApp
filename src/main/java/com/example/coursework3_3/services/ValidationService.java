package com.example.coursework3_3.services;

import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, Integer cottonMin, Integer cottonMax);
}