package com.example.coursework3_3.controllers;

import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.Socks;
import com.example.coursework3_3.services.StorageServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/storage")
@Tag(name = "Склад", description = "CRUD-операции и другие эндпоинты для работы")
public class StorageController {

    private final StorageServices storageServices;

    public StorageController(StorageServices storageServices) {
        this.storageServices = storageServices;
    }

    @PostMapping
    @Operation(summary = "Добавление носков на склад", description = "Можно добавить носки на склад по параметрам")
    public ResponseEntity<Long> addSocks(@RequestParam Color color, @RequestParam Size size,
                                         @RequestParam int cottonPart, @RequestParam Long count) {
        if (cottonPart < 0 || cottonPart > 100 || count < 0) {
            return ResponseEntity.notFound().build();
        } else {
            storageServices.addSocks(new Socks(color, size, cottonPart), count);
            return ResponseEntity.ok(count);
        }
    }

    @PutMapping
    @Operation(summary = "Отправление носков со склада",
            description = "Можно отправить носки со склада")
    public ResponseEntity<Long> sendSocks(@RequestParam Color color, @RequestParam Size size,
                                          @RequestParam int cottonPart, @RequestParam Long count) {
        if (count < 0) {
            return ResponseEntity.notFound().build();
        } else {
            Long a = storageServices.sendSocks(new Socks(color, size, cottonPart), count);
            if (a == 0L) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(count);
        }
    }

    @DeleteMapping
    @Operation(summary = "Удаление носков со склада без остатка",
            description = "Можно удалить носки со склада полностью")
    public ResponseEntity<Void> deleteSocks(@RequestParam Color color, @RequestParam Size size,
                                            @RequestParam int cottonPart) {
        if (storageServices.deleteSocks(new Socks(color, size, cottonPart))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Получение количества носков на складе",
            description = "Можно узнать количество носков с определенными параметрами на складе")
    public ResponseEntity<Long> SocksCount(@RequestParam Color color, @RequestParam Size size,
                                           @RequestParam int cottonPart) {
        Long n = storageServices.getSocksCount(new Socks(color, size, cottonPart));
        if (n == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(n);

    }

    @GetMapping("/downloadAllSocks")
    @Operation(summary = "Получение всех носков на складе", description = "Можно получить список всех носков")
    public ResponseEntity<Object> getStorageMap() {
        try {
            Path path = storageServices.getSocksMap();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN).contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Socks.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }

    }
}