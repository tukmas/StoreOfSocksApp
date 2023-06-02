package com.example.coursework3_3.services.impl;

import com.example.coursework3_3.exception.ValidationException;
import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.Socks;
import com.example.coursework3_3.model.SocksBatch;
import com.example.coursework3_3.repository.SocksRepository;
import com.example.coursework3_3.services.SocksStoreService;
import com.example.coursework3_3.services.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksStoreServiceImpl implements SocksStoreService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;


    @Override
    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);

        socksRepository.save(socksBatch);
    }

    @Override
    public Integer issuance(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public Integer reject(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public Integer getCount(Color color, Size size, Integer cottonMin, Integer cottonMax) {

        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = socksRepository.getAll();
        Integer result = 0;
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            if (socksItem.getKey().getColor().equals(color) &&
                    socksItem.getKey().getSize().equals(size) &&
                    socksItem.getKey().getCottonPart() >= cottonMin &&
                    socksItem.getKey().getCottonPart() <= cottonMax) {
                result += socksItem.getValue();
            }
        }
        return result;
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}