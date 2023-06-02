package com.example.coursework3_3.services.impl;

import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.SocksBatch;
import com.example.coursework3_3.services.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null &&
                socksBatch.getQuantity() > 0 &&
                socksBatch.getSocks().getColor() != null &&
                socksBatch.getSocks().getSize() != null &&
                checkCotton(socksBatch.getSocks().getCottonPart(), socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        return color != null &&
                size != null &&
                checkCotton(cottonMin, cottonMax);
    }

    private boolean checkCotton(Integer cottonMin, Integer cottonMax) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
