package com.example.teams.cli.service;

import com.example.teams.model.Team;
import com.example.teams.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Получение всех команд из таблицы
     * @return список команд
     */
    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    /**
     * Создание новой команды
     */
    public void create(Team team){
        teamRepository.save(team);
    }

    /**
     * Удаление команды по указанному индексу
     */
    @Transactional
    public void deleteById(long id){
        teamRepository.deleteById(id);
    }

    /**
     * Поиск объекта по идентификатору
     * @param id
     * @return
     */
    @Transactional
    public Optional<Team> findById(long id){

        return teamRepository.findById(id);
    }
}
