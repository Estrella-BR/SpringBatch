package com.ccsw.tutorialbatch.config;

import com.ccsw.tutorialbatch.mapper.GameRowMapper;
import com.ccsw.tutorialbatch.model.Game;
import com.ccsw.tutorialbatch.model.GameAvailability;
import com.ccsw.tutorialbatch.processor.GameItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class GameBatchConfiguration {

    @Bean
    public ItemReader<Game> readerCategory(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Game>().name("gameItemReader").dataSource(dataSource).sql("SELECT game_id, title, age, stock FROM game").rowMapper(new GameRowMapper()).build();
    }

    @Bean
    public ItemProcessor<Game, GameAvailability> processorGame() {

        return new GameItemProcessor();
    }

    @Bean
    public ItemWriter<GameAvailability> writerGame() {
        return new FlatFileItemWriterBuilder<GameAvailability>().name("writerGame").resource(new FileSystemResource("target/test-outputs/game-output.txt")).lineAggregator(new PassThroughLineAggregator<>()).build();
    }

    @Bean
    public Step step1Game(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemReader<Game> readerGame, ItemProcessor<Game, GameAvailability> processorGame, ItemWriter<GameAvailability> writerGame) {
        return new StepBuilder("step1Game", jobRepository).<Game, GameAvailability>chunk(10, transactionManager).reader(readerGame).processor(processorGame).writer(writerGame).build();
    }

    @Bean
    public Job jobGame(JobRepository jobRepository, Step step1Game) {
        return new JobBuilder("jobGame", jobRepository).incrementer(new RunIdIncrementer()).flow(step1Game).end().build();
    }
}
