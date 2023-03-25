package com.example.teams.cli.service;

import com.example.teams.model.Position;
import com.example.teams.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    @Autowired
    private PositionRepository repository;

    /**
     * Получение всех игровых амплуа из таблицы
     * @return список команд
     */
    public List<Position> getAll(){
        return repository.findAll();
    }

    /**
     * Добавление нового игрового амплуа
     */
    public void create(Position position){
        repository.save(position);
    }

    /**
     * Удаление игрового амплуа по указанному индексу
     */
    public void deleteById(long id){
        repository.deleteById(id);
    }

    /**
     * Поиск игрового амплуа по идентификатору
     * @param id
     * @return
     */
    public Optional<Position> findById(long id){
        return repository.findById(id);
    }
}
