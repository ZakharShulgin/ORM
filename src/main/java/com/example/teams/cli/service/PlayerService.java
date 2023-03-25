package com.example.teams.cli.service;

import com.example.teams.model.Player;
import com.example.teams.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Получение всех игроков из таблицы
     * @return список игроков
     */
    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    /**
     * Добавление нового игрока в таблицу
     */
    public void create(Player player){
        playerRepository.save(player);
    }

    /**
     * Обновление игрока по указанному id
     *
     * @return
     */
    @Transactional
    public Optional<Player> findById(long id){
        return playerRepository.findById(id);
    }

    /**
     * Удаление игрока по указанному индексу
     */
    public void deleteById(long id){

        playerRepository.deleteById(id);

    }
}
