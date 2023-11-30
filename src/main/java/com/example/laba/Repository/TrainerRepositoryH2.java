package com.example.laba.Repository;

import com.example.laba.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.laba.models.Trainer;

import java.util.List;

@Repository

public class TrainerRepositoryH2 implements TrainerRepository {
    private static final String CREATE = """
                        insert into trainers (id, name, surname, patronymic, gender, birth, salary)
                        values (:id, :name, :surname, :patronymic, :gender, :birth, :salary)
            """;

    private final RowMapper<Trainer> rowMapper = new DataClassRowMapper<>(Trainer.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TrainerRepositoryH2(JdbcTemplate jdbcTemplate,
                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Trainer read(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from trainers where id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Trainer with id = [" + id + "] not found", e);
        }
    }

    @Override
    public List<Trainer> readAll() {
        return jdbcTemplate.query("select * from trainers", rowMapper);
    }

    @Override
    public void create(Trainer trainer) {
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(trainer);
        namedParameterJdbcTemplate.update(CREATE, paramsSource);
    }
}
