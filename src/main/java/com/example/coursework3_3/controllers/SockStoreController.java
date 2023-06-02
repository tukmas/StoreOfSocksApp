package com.example.coursework3_3.controllers;

import com.example.coursework3_3.controllers.dto.ResponseDto;
import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.SocksBatch;
import com.example.coursework3_3.services.SocksStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков", description = "регистрация прихода, отпуск со склада, списание брака, подсчет кол-ва")
@RequiredArgsConstructor
public class SockStoreController {

    private final SocksStoreService storeService;

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад")
    @ApiResponse(responseCode = "200", description = "Операция прошла успешно")
    @ApiResponse(responseCode = "400", description = "Параметр запроса отсутствует или имеет некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> accept(@RequestBody SocksBatch socksBatch) {
        storeService.accept(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Носки добавлены на склад"));
    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков на склад")
    @ApiResponse(responseCode = "200", description = "Операция прошла успешно")
    @ApiResponse(responseCode = "400", description = "Параметр запроса отсутствует или имеет некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> issuance(@RequestBody SocksBatch socksBatch) {
        Integer socksCount = storeService.issuance(socksBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount + " было отпущено со склада"));
    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponse(responseCode = "200", description = "Операция прошла успешно")
    @ApiResponse(responseCode = "400", description = "Параметр запроса отсутствует или имеет некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> getCount(@RequestParam Color color,
                                                @RequestParam Size size,
                                                @RequestParam Integer cottonMin,
                                                @RequestParam Integer cottonMax) {
        Integer socksCount = storeService.getCount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponseDto("Количество носков: " + socksCount));
    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200", description = "Операция прошла успешно")
    @ApiResponse(responseCode = "400", description = "Параметр запроса отсутствует или имеет некорректный формат")
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка приложения")
    public ResponseEntity<ResponseDto> reject(@RequestBody SocksBatch socksBatch) {
        Integer socksCount = storeService.reject(socksBatch);
        return ResponseEntity.ok(new ResponseDto(socksCount + " носков списано со склада"));
    }
}