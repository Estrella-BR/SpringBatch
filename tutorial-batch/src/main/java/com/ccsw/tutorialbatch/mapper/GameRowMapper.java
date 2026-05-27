package com.ccsw.tutorialbatch.mapper;

import com.ccsw.tutorialbatch.model.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowMapper implements RowMapper<Game> {
    public static final String ID_COLUMN = "game_id";
    public static final String TITLE_COLUMN = "title";
    public static final String AGE_COLUMN = "age";
    public static final String STOCK_COLUMN = "stock";

    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();

        game.setTitle(rs.getString(TITLE_COLUMN));
        game.setAge(rs.getInt(AGE_COLUMN));
        game.setStock(rs.getInt(STOCK_COLUMN));

        return game;
    }
}
