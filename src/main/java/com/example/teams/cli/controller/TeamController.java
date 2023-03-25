package com.example.teams.cli.controller;

import com.example.teams.cli.service.TeamService;
import com.example.teams.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

@Component
public class TeamController {

    private Scanner scanner;

    @Autowired
    private TeamService service;

    public TeamController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображение списка команд в виде таблицы
     */
    public void getAll(){
        System.out.println("-".repeat(69));
        System.out.format("|%2s|%15s|%20s|%15s|%6s|%4s|\n", "N", "Название", "Тренер", "Страна", "Год", "Счет");
        System.out.println("-".repeat(69));
        service.getAll().forEach( elem -> {
            System.out.format("|%2d|%15s|%20s|%15s|%6s|%4d|\n", elem.getId(), elem.getName(),
                                              elem.getCoach(), elem.getCountry(),
                                              elem.getFoundation(), elem.getScore());
            System.out.println("-".repeat(69));
        });
    }

    /**
     * Форма создания новой команды (Интерактив по типу useradd в linux)
     */
    public void create(){
        try {
            Team team = new Team();
            System.out.println("Введите название команды:");
            team.setName(scanner.nextLine());
            System.out.println("Введите имя главного тренера команды:");
            team.setCoach(scanner.nextLine());
            System.out.println("Введите год основания:");
            team.setFoundation(scanner.nextLine());
            System.out.println("Введите страну команды:");
            team.setCountry(scanner.nextLine());
            System.out.println("Введите счет:");
            team.setScore(Integer.parseInt(scanner.nextLine()));
            team.setPlayers(new HashSet<>());
            service.create(team);
            System.out.println("Команда создана!");
        } catch (Exception e){
            System.out.println("Ошибка при вводе данных! Попробуйте еще раз!");
        }
    }

    /**
     * Форма обновления команды
     */
    public void update(){
        this.getAll();
        System.out.println("Введите номер id, который необходимо изменить");
        try {
            long id = Long.parseLong(scanner.nextLine());
            Optional<Team> teamData = service.findById(id);
            if (teamData.isPresent()) {
                Team team = teamData.get();
                System.out.printf("Найдена команда с id=%d. Это ФК %s%n", id, team.getName());
                printUpdate();
                int value = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (value) {
                    case 1 -> {
                        System.out.println("Введите новое название:");
                        team.setName(scanner.nextLine());
                    }
                    case 2 -> {
                        System.out.println("Введите имя нового главного тренера:");
                        team.setCoach(scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.println("Введите новый счет:");
                        team.setScore(Integer.parseInt(scanner.nextLine()));
                    }
                    default -> System.out.println("Указан неверный пункт выбора");
                }
                service.create(team);
            }else{
                System.out.println("Команды с указанным id в списке нет!");
            }
        } catch (Exception e){
            System.out.println("Ошибка при вводе id, повторите!");
        }
    }

    /**
     * Форма удаления команды
     */
    public void delete(){
        this.getAll();
        System.out.println("Введите номер id, который необходимо удалить");
        try{
            service.deleteById(Long.parseLong(scanner.nextLine()));
            System.out.println("Команда удалена! Игроки данной команды также удалены из базы!");
        } catch (Exception e){
            System.out.println("Команды с введенным id не существует в базе!");
        }
    }


    private void printUpdate(){
        System.out.println("Выберите один из пунктов для изменения");
        System.out.println("1.Новое название");
        System.out.println("2.Новый главный тренер");
        System.out.println("3.Новый счет");
    }
}
