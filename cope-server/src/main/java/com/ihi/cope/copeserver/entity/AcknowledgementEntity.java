package com.ihi.cope.copeserver.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "acknowledgement")
@Table(name = "acknowledgement")
public class AcknowledgementEntity {

    @Id
    private String uuid;

    private String operation;
    private boolean success;

    @OneToMany(mappedBy = "acknowledgement", fetch = FetchType.EAGER)
    private List<ParameterEntity> input;

    private LocalDateTime startTime;
    private LocalDateTime completeTime;

    @Lob
    private String output;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "clientId")
    @NotNull
    private ClientEntity client;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String id) {
        this.uuid = id;
    }

    public List<ParameterEntity> getInput() {
        return input;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setInput(List<ParameterEntity> input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
