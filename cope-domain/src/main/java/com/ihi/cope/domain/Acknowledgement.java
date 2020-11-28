package com.ihi.cope.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Acknowledgement {
    private String uuid;
    private String operation;
    private boolean success;

    @Builder.Default
    private List<Parameter> input = null;
    private Object output;
    private LocalDateTime startTime;
    private LocalDateTime completeTime;
    private Client client;

    public List<Parameter> getInput() {
        if (input == null) {
            input = new ArrayList<>();
        }
        return input;
    }
}
