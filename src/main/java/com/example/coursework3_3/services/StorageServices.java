package com.example.coursework3_3.services;

import com.example.coursework3_3.model.Socks;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageServices {

    Socks addSocks(Socks socks1, Long count);

    Long sendSocks(Socks socks1, Long count);

    Long getSocksCount(Socks socks1);

    boolean deleteSocks(Socks socks);

    Path getSocksMap() throws IOException;
}