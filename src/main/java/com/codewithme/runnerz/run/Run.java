package com.codewithme.runnerz.run;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version // Just to keep the track of the row to know if its a new row or older
) {

    public Run {
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("CompletedOn must be after startedOn");
        }
    }
}
