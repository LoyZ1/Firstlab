package com.example.laba.Repository;

import com.example.laba.NotFoundException;


import com.example.laba.models.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class GymRepositoryH2 implements GymRepository{
    private static final String CREATE = """
                        insert into gyms (id, ClubName, trainerId)
                        values (:id, :ClubName, :trainerId)
            """;

    private final RowMapper<Gym> rowMapper = new DataClassRowMapper<>(Gym.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GymRepositoryH2(JdbcTemplate jdbcTemplate,
                                            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Gym read(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from gyms where id = ?", rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Gym with id = [" + id + "] not found", e);
        }
    }

    @Override
    public List<Gym> readAll() {
        return jdbcTemplate.query("select * from gyms", rowMapper);
    }

    @Override
    public void Create(Gym gym) {
        BeanPropertySqlParameterSource paramsSource = new BeanPropertySqlParameterSource(gym);
        namedParameterJdbcTemplate.update(CREATE, paramsSource);
    }
}
