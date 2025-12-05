package com.codewithme.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        return this.runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = this.runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunNotFoundException();
        }
        return run.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
        runRepository.save(run);
    }


    // put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    void update(@Valid @RequestBody Run run){
        runRepository.save(run);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/count")
    Long count(){
        return this.runRepository.count();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/")
    List<Run> findByLocation(@RequestParam(defaultValue = "INDOOR") String location){
        return this.runRepository.findAllByLocation(Location.valueOf(location));
    }


}
