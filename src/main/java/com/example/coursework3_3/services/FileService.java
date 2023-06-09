package com.example.coursework3_3.services;

import java.io.File;
import java.nio.file.Path;

public interface FileService {

    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();

    Path createTempFile(String suffix);
}
