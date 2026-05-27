package com.ccsw.tutorialbatch.processor;

import com.ccsw.tutorialbatch.model.Game;
import com.ccsw.tutorialbatch.model.GameAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class GameItemProcessor implements ItemProcessor<Game, GameAvailability> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameItemProcessor.class);

    public GameAvailability process(final Game game) {
        String title = game.getTitle();
        String availability = "Not available";

        if (game.getStock() > 0)
            availability = "Available";

        GameAvailability transformedGame = new GameAvailability(title, availability);
        LOGGER.info("Converting ( {} ) into ( {} )", game, transformedGame);

        return transformedGame;
    }
}
