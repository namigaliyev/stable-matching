package main.stable.matching.example;

import main.stable.matching.core.StableMatching;
import main.stable.matching.entity.ParticipantEntity;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var result = StableMatching.builder()
                .withClient()
                .fromFile("main/stable/matching/data/clients.txt")
                .build()
                .withProvider()
                .fromList(List.of(
                        new ParticipantEntity("provider", false, List.of("client", "client2", "client1", "client3")),
                        new ParticipantEntity("provider1", false, List.of("client", "client2", "client3", "client1")),
                        new ParticipantEntity("provider2", false, List.of("client3", "client1", "client", "client2")),
                        new ParticipantEntity("provider3", false, List.of("client", "client1", "client3", "client2"))
                ))
                .build()
                .doMatch();

        for(Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("{" + entry.getKey() + "," + entry.getValue() + "}");
        }
    }
}
