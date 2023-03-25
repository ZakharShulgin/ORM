package com.example.teams.cli.controller;

import com.example.teams.cli.service.PlayerService;
import com.example.teams.cli.service.PositionService;
import com.example.teams.cli.service.TeamService;
import com.example.teams.model.Player;
import com.example.teams.model.Position;
import com.example.teams.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class PlayerController {

    private Scanner scanner;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PositionService positionService;

    public PlayerController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Отображение списка команд в виде таблицы
     */
    public void getAll(){
        System.out.println("-".repeat(119));
        System.out.format("|%5s|%15s|%15s|%15s|%3s|%10s|%10s|%15s|%10s|%10s|\n", "N", "Имя",
                "Отчество", "Фамилия","Лет", "Страна",
                "Команда", "Амплуа", "Зарплата", "Капитан");
        System.out.println("-".repeat(119));
        playerService.getAll().forEach( elem -> {
            System.out.format("|%5d|%15s|%15s|%15s|%3d|%10s|%10s|%15s|%10d|%10s|\n", elem.getId(), elem.getFirstName(),
                    elem.getSecondName(), elem.getLastName(),elem.getAge(),  elem.getCountry(),
                    elem.getTeam().getName(), elem.getPosition().getName(), elem.getSalary(), elem.isLeader() ? "Да" : "Нет");
            System.out.println("-".repeat(119));
        });
    }

    /**
     * Форма создания новой команды (Интерактив по типу useradd в linux)
     */
    public void create(){
        boolean flag = true;
        if(teamService.getAll().size() == 0){
            System.out.println("Невозможно создать игрока, пока нет ни одной команды!");
        } else if(positionService.getAll().size() == 0){
            System.out.println("Невозможно создать игрока, пока нет ни одной игровой позиции!");
        } else {
            try {
                Player player = new Player();
                System.out.println("Введите имя игрока:");
                player.setFirstName(scanner.nextLine());
                System.out.println("Введите отчество игрока:");
                player.setSecondName(scanner.nextLine());
                System.out.println("Введите фамилию игрока:");
                player.setLastName(scanner.nextLine());
                System.out.println("Введите возраст игрока:");
                player.setAge(Integer.parseInt(scanner.nextLine()));
                System.out.println("Введите страну рождения игрока:");
                player.setCountry(scanner.nextLine());
                System.out.println("Укажите id команды, за которую будет выступать данный игрок");
                teamService.getAll().forEach(elem -> System.out.format("|%5d|%15s|\n", elem.getId(), elem.getName()));
                Optional<Team> teamData = teamService.findById(Long.parseLong(scanner.nextLine()));
                if (teamData.isPresent()) {
                    Team team = teamData.get();
                    player.setTeam(team);
                } else {
                    System.out.println("Команды по указанному id не существует");
                    flag = false;
                }
                System.out.println("Укажите id игровой позиции, на которой будет выступать данный игрок");
                positionService.getAll().forEach(elem -> System.out.format("|%5d|%15s|\n", elem.getId(), elem.getName()));
                Optional<Position> positionData = positionService.findById(Long.parseLong(scanner.nextLine()));
                if (positionData.isPresent()) {
                    Position position = positionData.get();
                    player.setPosition(position);
                } else {
                    System.out.println("Игровой позиции по указанному id не существует");
                    flag = false;
                }
                System.out.println("Введите зарплату игрока:");
                player.setSalary(Integer.parseInt(scanner.nextLine()));
                System.out.println("Игрок - капитан?(true или false)");
                player.setLeader(Boolean.parseBoolean(scanner.nextLine()));
                if (flag) {
                    playerService.create(player);
                    System.out.println("Игрок создан и добавлен!");
                } else {
                    System.out.println("При добавлении одного из полей возникла ошибка, повторите!");
                }
            } catch (Exception e){
                System.out.println("Произошла ошибка при вводе данных! Попробуйте еще раз");
            }
        }
    }

    /**
     * Форма обновления игрока
     */
    public void update(){
        this.getAll();
        System.out.println("Введите номер id, который необходимо изменить");
        try {


            long id = Long.parseLong(scanner.nextLine());
            Optional<Player> playerData = playerService.findById(id);
            if (playerData.isPresent()) {
                Player player = playerData.get();
                System.out.printf("Найден игрок с id=%d. Это %s %s%n", id, player.getFirstName(), player.getLastName());
                printUpdate();
                int value = Integer.parseInt(scanner.nextLine());
                switch (value) {
                    case 1 -> {
                        System.out.println("Введите новый возраст:");
                        player.setAge(Integer.parseInt(scanner.nextLine()));
                    }
                    case 2 -> {
                        System.out.println("Введите новую зарплату:");
                        player.setSalary(Integer.parseInt(scanner.nextLine()));
                    }
                    case 3 -> {
                        System.out.println("Делаем капитаном (true)/ Снимаем с капитанства (false):");
                        player.setLeader(Boolean.parseBoolean(scanner.nextLine()));
                    }
                    default -> System.out.println("Указан неверный пункт выбора");
                }
                playerService.create(player);
                System.out.println("Данные игрока обновлены");
            } else {
                System.out.println("Игрока с указанным id в списке нет!");
            }
        }catch (Exception e){
            System.out.println("Указан неправильный id,повторите!");
        }
    }

    /**
     * Форма удаления игрока
     */
    public void delete(){
        this.getAll();
        System.out.println("Введите номер id, который необходимо удалить");
        try {
            playerService.deleteById(Long.parseLong(scanner.nextLine()));
            System.out.println("Игрок удален!");
        } catch (Exception e){
            System.out.println("Игрока с указанным id в базе нет!");
        }

    }

    private void printUpdate(){
        System.out.println("Выберите один из пунктов для изменения");
        System.out.println("1.Изменить возраст");
        System.out.println("2.Изменить зарплату");
        System.out.println("3.Изменить капитанство");
    }

}