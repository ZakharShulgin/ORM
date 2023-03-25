package com.example.teams.cli.controller;

import com.example.teams.cli.service.PositionService;
import com.example.teams.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class PositionController {

    private Scanner scanner;

    @Autowired
    private PositionService service;

    public PositionController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображение списка команд в виде таблицы
     */
    public void getAll(){
        System.out.println("-".repeat(23));
        System.out.format("|%5s|%15s|\n", "N", "Амплуа");
        System.out.println("-".repeat(23));
        service.getAll().forEach( elem -> {
            System.out.format("|%5d|%15s|\n", elem.getId(), elem.getName());
            System.out.println("-".repeat(23));
        });
    }

    /**
     * Форма создания нового игрового амплуа
     */
    public void create(){
        Position position = new Position();
        System.out.println("Введите название игрового амплуа:");
        position.setName(scanner.nextLine());
        service.create(position);
        System.out.println("Новое игровое амплуа добавлено!");
    }

    /**
     * Форма обновления игрового амплуа
     */
    public void update(){
        this.getAll();
        System.out.println("Введите номер id амплуа, который необходимо изменить");
        try {
            long id = Long.parseLong(scanner.nextLine());
            Optional<Position> positionData = service.findById(id);
            if (positionData.isPresent()) {
                Position position = positionData.get();
                System.out.printf("Найдена игровая позиция с id=%d. Это %s%n", id, position.getName());
                printUpdate();
                int value = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                System.out.println(value);
                if (value == 1) {
                    System.out.println("Введите новое название данной игровой позиции:");
                    position.setName(scanner.nextLine());
                } else {
                    System.out.println("Указан неверный пункт выбора");
                }
                service.create(position);
            } else {
                System.out.println("Игрового амплуа с указанным id в списке нет!");
            }
        } catch (Exception e){
            System.out.println("Указан неправильный id, повторите!");
        }
    }

    /**
     * Форма удаления игрового амплуа
     */
    public void delete(){
        this.getAll();
        System.out.println("Введите номер id, который необходимо удалить");
        try {
            service.deleteById(Long.parseLong(scanner.nextLine()));
            System.out.println("Игровое амплуа удалено! Игроки данного амплуа также удалены!");
        } catch (Exception e){
            System.out.println("Указанного id нет!");
        }
    }


    private void printUpdate(){
        System.out.println("Выберите один из пунктов для изменения (номер пункта)");
        System.out.println("1.Новое название");
    }
}
