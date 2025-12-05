package com.codewithme.runnerz.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;

/** This class only use the plain JDBC to connect and query database
 *
 */

@Repository
public class JdbcClientRunRepository {
    private static final Logger logger = LoggerFactory.getLogger(JdbcClientRunRepository.class);

    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Run> findAll() {
        return this.jdbcClient.sql("SELECT * FROM run")
                .query(Run.class)
                .list();
    }

    Optional<Run> findById(Integer id) {
        return this.jdbcClient.sql("select * from run where id = :id")
                .param(id)
                .query(Run.class)
                .optional();
    }

    void create(Run run) {
        this.jdbcClient.sql("INSERT INTO run (id,title,started_on,completed_on,miles,location) VALUES (?, ?, ?, ?, ?, ?)")
                .params(List.of(
                        run.id(),
                        run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString()
                ))
                .update();
    }

    void update(Run run, Integer id){
        this.jdbcClient.sql("update run set titel = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();
    }

    void delete(Integer id){
        var rowsUpdated = this.jdbcClient.sql("delete from run where id = :id")
                .param(id)
                .update();

        Assert.state(rowsUpdated == 1, "Failed to delete run with id " + id);
    }

    int count(){
        return this.jdbcClient.sql("select * from run")
                .query()
                .listOfRows()
                .size();
    }

    void saveAll(List<Run> runs){
        logger.info("Saving {} runs", runs.size());
        runs.forEach(this::create);
    }

    List<Run> findByLocation(String location){
        return this.jdbcClient.sql("select * from run where location = :location")
                .param(location)
                .query(Run.class)
                .list();
    }
}
