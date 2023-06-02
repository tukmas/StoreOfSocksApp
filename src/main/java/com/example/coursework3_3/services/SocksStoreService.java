package com.example.coursework3_3.services;

import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.SocksBatch;

public interface SocksStoreService {

    void accept(SocksBatch socksBatch);

    Integer issuance(SocksBatch socksBatch);

    Integer reject(SocksBatch socksBatch);

    Integer getCount(Color color, Size size, Integer cottonMin, Integer cottonMax);

}