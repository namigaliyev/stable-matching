package main.stable.matching.entity;

import java.util.ArrayList;
import java.util.List;

public class StableMatchingEntity {
    private List<ParticipantEntity> clients;
    private List<ParticipantEntity> providers;

    public StableMatchingEntity() {
        this.clients = new ArrayList<>();
        this.providers = new ArrayList<>();
    }

    public List<ParticipantEntity> getClients() {
        return this.clients;
    }

    public void setClients(List<ParticipantEntity> participants) {
        this.clients = participants;
    }

    public List<ParticipantEntity> getProviders() {
        return this.providers;
    }

    public void setProviders(List<ParticipantEntity> participants) {
        this.providers = participants;
    }
}
