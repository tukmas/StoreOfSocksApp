package com.example.coursework3_3.repository;

import com.example.coursework3_3.model.Socks;
import com.example.coursework3_3.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {
    void save(SocksBatch socksBatch);

    Integer remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}