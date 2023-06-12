package com.example.coursework3_3.service;

import com.example.coursework3_3.model.Color;
import com.example.coursework3_3.model.Size;
import com.example.coursework3_3.model.Sock;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class SockService {

    private final ArrayList<Sock> socksList = new ArrayList<Sock>();

    @PostConstruct
    void one() {
        Sock sock = new Sock(20, 100, Size.L, Color.White);
        Sock sock2 = new Sock(20, 300, Size.L, Color.White);
        Sock sock3 = new Sock(20, 100, Size.M, Color.White);
        Sock sock4 = new Sock(20, 40, Size.M, Color.White);
        System.out.println(addSocks(sock));
        System.out.println(addSocks(sock2));
        System.out.println(addSocks(sock3));
        System.out.println(addSocks(sock4));
    }

    private void addNewSocks(Sock sock) {
        if (sock != null) {
            socksList.add(sock);
        }
    }

    private void plusSocks(int id, Sock sock) {
        if ((sock != null) && (socksList.get(id) != null)) {
            socksList.get(id).setQuantity(socksList.get(id).getQuantity() + sock.getQuantity());
        }
    }

    public String addSocks(Sock socks) {
        if (socks == null) {
            return "Передан пустой запрос\n";
        } else {
            for (Sock s : socksList) {
                if ((s.getColor() == socks.getColor()) && (s.getCottonPart() == socks.getCottonPart()) && (s.getSize() == socks.getSize())) {
                    String str = "Пополнена партия носков: " + s + " + " + socks.getQuantity() + " шт.";
                    int id = socksList.indexOf(s);
                    plusSocks(id, socks);
                    return str + " Общее количество - " + socksList.get(id).getQuantity() + " шт.\n";
                }
            }
            addNewSocks(socks);
            return "Добавлена партия носков: " + socks;
        }
    }

    public String showSocks() {
        if (socksList.isEmpty()) {
            return "На складе не обнаружено ни одного носка";
        } else {
            return socksList.toString();
        }
    }

    public String findSocks(String string) {
        String answer = "";
        String s2 = string.replace(" ", "").replace("%", "");
        try {
            int s3 = Integer.parseInt(s2);
            for (Sock sock : socksList) {
                if (sock.getCottonPart() == s3) {
                    answer = answer + sock;
                }
            }
        } catch (NumberFormatException ignored) {
        }
        for (Sock sock : socksList) {
            if (sock.getColor().toString().equals(s2.toLowerCase())) {
                answer = answer + sock;
            }
        }
        for (Sock sock : socksList) {
            if (sock.getSize().toString().equals(s2.toUpperCase())) {
                answer = answer + sock;
            }
        }
        if (answer.isEmpty()) {
            answer = answer + "По данному запросу носков не найдено";
        }
        System.out.println(answer);
        return answer;
    }

    public Sock checkSock(String color, String size, int cottonPart) {
        for (Sock sock : socksList) {
            if ((sock.getColor().toString().equals(color.trim().toLowerCase())) && (sock.getSize().toString().equals(size.trim().toUpperCase())) && (sock.getCottonPart() == cottonPart)) {
                return sock;
            }
        }
        return null;
    }

    public boolean can(Sock sock, int count) {
        if (sock == null) {
            return false;
        }
        if (count <= 0) {
            return false;
        }
        return (sock.getQuantity() - count) >= 0;
    }

    public String putSocks(Sock sock, int count) {
        if (sock == null) {
            return "По данным параметрам товаров не найдено";
        }
        if (count <= 0) {
            return "Количество отпускаемого товара должно быть больше 0";
        }
        if ((sock.getQuantity() - count) < 0) {
            return "Нельзя отпустить товаров больше, чем есть на складе";
        } else {
            String demosock = sock.toString();
            sock.setQuantity(sock.getQuantity() - count);
            return "Из партии:" + demosock + "Отпустили " + count + " носков. Осталось - " + sock.getQuantity() + "шт.\n";
        }
    }

    public String deleteSocks (Sock sock, int count) {
        String answer = putSocks(sock, count);
        if (answer.contains("отпускаемого")) {answer = answer.replace("отпускаемого", "удаляемого");}
        if (answer.contains("отпустить")) {answer = answer.replace("отпустить", "удалить");}
        if (answer.contains("Отпустили")) {answer = answer.replace("Отпустили", "Удалено");}
        return answer;
    }
}

