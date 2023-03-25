package com.example.teams.cli;

import com.example.teams.cli.controller.PlayerController;
import com.example.teams.cli.controller.PositionController;
import com.example.teams.cli.controller.TeamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {
    private Scanner scanner;

    @Autowired
    private TeamController teamController;

    @Autowired
    private PlayerController playerController;

    @Autowired
    private PositionController positionController;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Главное меню программы
     */
    public void menu(){
        while (true){
            printMenuItem();
            int data = getValue();
            switch(data){
                case 1 -> teamMenu();
                case 2 -> playerMenu();
                case 3 -> positionMenu();
                case 4 -> System.exit(0);
                default -> System.out.println("Нет указанного пункта меню!");
            }
        }
    }

    private int getValue() {
        System.out.println("Выберите пункт меню, указав номер пункта:");
        int value;
        try{
            value = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            value = 0;
        }
        return value;
    }

    private void teamMenu(){
        teamExit: while (true){
            printTeamItem();
            int data = getValue();
            switch(data){
                case 1 -> teamController.getAll();
                case 2 -> teamController.create();
                case 3 -> teamController.update();
                case 4 -> teamController.delete();
                case 5 -> {break teamExit;}
                default -> System.out.println("Нет указанного пункта меню!");
            }
        }
    }

    private void playerMenu(){
        playerExit: while (true){
            printPlayerItem();
            int data = getValue();
            switch(data){
                case 1 -> playerController.getAll();
                case 2 -> playerController.create();
                case 3 -> playerController.update();
                case 4 -> playerController.delete();
                case 5 -> {break playerExit;}
                default -> System.out.println("Нет указанного пункта меню!");
            }
        }
    }
    private void positionMenu(){
        positionExit: while (true){
            printPositionItem();
            int data = getValue();
            switch(data){
                case 1 -> positionController.getAll();
                case 2 -> positionController.create();
                case 3 -> positionController.update();
                case 4 -> positionController.delete();
                case 5 -> {break positionExit;}
                default -> System.out.println("Нет указанного пункта меню!");
            }
        }
    }
    private void printMenuItem(){
        System.out.println("1.Команды");
        System.out.println("2.Игроки");
        System.out.println("3.Игровые позиции");
        System.out.println("4.Выход из программы");
    }
    private void printTeamItem(){
        System.out.println("1.Список команд");
        System.out.println("2.Добавить новую команду");
        System.out.println("3.Редактировать команду");
        System.out.println("4.Удалить команду");
        System.out.println("5.Назад");
    }

    private void printPlayerItem(){
        System.out.println("1.Список игроков");
        System.out.println("2.Добавить нового игрока");
        System.out.println("3.Редактировать игрока");
        System.out.println("4.Удалить игрока");
        System.out.println("5.Назад");
    }

    private void printPositionItem(){
        System.out.println("1.Список игровых амплуа");
        System.out.println("2.Добавить новое амплуа");
        System.out.println("3.Редактировать амплуа");
        System.out.println("4.Удалить амплуа");
        System.out.println("5.Назад");
    }
}
