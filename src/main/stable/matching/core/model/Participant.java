package main.stable.matching.core.model;

import main.stable.matching.core.StableMatching;
import main.stable.matching.entity.ParticipantEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public abstract class Participant {
    protected final StableMatching stableMatching;
    protected final List<ParticipantEntity> participants;
    private static final String COMMA_DELIMITER = ",";

    public Participant(StableMatching matching) {
        this.stableMatching = matching;
        this.participants = new ArrayList<>();
    }

    public Participant fromFile(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String name = values[0];
                this.participants.add(new ParticipantEntity(name, false,
                        new ArrayList<>(Arrays.asList(Arrays.copyOfRange(values, 1, values.length)))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Participant fromList(List<ParticipantEntity> participants) {
        this.participants.addAll(participants);
        return this;
    }

    public abstract StableMatching build();
}
