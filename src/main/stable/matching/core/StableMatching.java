package main.stable.matching.core;

import main.stable.matching.core.model.ClientParticipant;
import main.stable.matching.core.model.Participant;
import main.stable.matching.core.model.ProviderParticipant;
import main.stable.matching.entity.ParticipantEntity;
import main.stable.matching.entity.StableMatchingEntity;

import java.util.*;
import java.util.stream.Collectors;

public class StableMatching {
    private final StableMatchingEntity stableMatchEntity;
    private Map<String, String> matchedMap;

    public StableMatching() {
        this.stableMatchEntity = new StableMatchingEntity();
    }

    public StableMatchingEntity getStableMatchEntity() {
        return this.stableMatchEntity;
    }

    public static StableMatching builder() {
        return new StableMatching();
    }

    public Participant withClient() {
        return new ClientParticipant(this);
    }

    public Participant withProvider() {
        return new ProviderParticipant(this);
    }

    public Map<String, String> doMatch() {
        this.matchedMap = new HashMap<>();

        ParticipantEntity client;
        while ((client = getMisMatchedParticipant()) != null) {

            for (String clientRef : client.getReferences()) {

                var providerReferences = stableMatchEntity.getProviders()
                        .stream()
                        .filter(pr -> pr.getName().equals(clientRef))
                        .findAny()
                        .map(ParticipantEntity::getReferences);


                if (providerReferences.isPresent() &&
                        providerReferences.get().contains(client.getName())) {

                    var selectedClient = this.matchedMap.get(clientRef);

                    if (selectedClient != null) {
                        int currentClientDistance = providerReferences.get().indexOf(client.getName());
                        int selectedClientDistance = providerReferences.get().indexOf(selectedClient);

                        if (currentClientDistance < selectedClientDistance) {
                            dropMap(clientRef, selectedClient);
                            addMap(client, clientRef);
                            break;
                        }
                    } else {
                        addMap(client, clientRef);
                        break;
                    }
                }
            }
        }
        return this.matchedMap;
    }

    private ParticipantEntity getMisMatchedParticipant() {
        return stableMatchEntity.getClients()
                .stream()
                .filter(participant -> !participant.isMatched())
                .findAny()
                .orElse(null);
    }

    private void addMap(ParticipantEntity client, String clientReference) {
        this.matchedMap.put(clientReference, client.getName());
        updateMatchedStatus(client.getName(), true);
        dropClientReference(client);
    }

    private void dropMap(String clientReference, String selectedClient) {
        this.matchedMap.remove(clientReference);
        updateMatchedStatus(selectedClient, false);
    }

    private void dropClientReference(ParticipantEntity client) {
        this.stableMatchEntity.getClients()
                .stream()
                .filter(pr -> pr.equals(client))
                .map(ParticipantEntity::getReferences)
                .collect(Collectors.toList())
                .remove(client.getName());
    }

    private void updateMatchedStatus(String client, boolean isMatched) {
        this.stableMatchEntity.getClients()
                .stream().filter(pr -> pr.getName().equals(client))
                .findAny()
                .get().setMatched(isMatched);
    }
}
