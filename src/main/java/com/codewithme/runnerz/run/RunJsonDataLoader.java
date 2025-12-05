package com.codewithme.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
        logger.info("RunJsonDataLoader bean created!");
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading some initial run data...");
        if(runRepository.count()==0){
            try(InputStream inputStream =  TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                logger.info("Reading {} from JSON data and saving to database", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            }
        } else {
            logger.info("Not Loading all initial run data because runs already exist!");
        }
    }

    @Override
    public String toString() {
        return "RunJsonDataLoader{" +
                "runRepository=" + runRepository +
                ", objectMapper=" + objectMapper +
                '}';
    }
}
