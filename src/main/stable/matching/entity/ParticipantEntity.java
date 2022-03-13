package main.stable.matching.entity;

import java.util.ArrayList;
import java.util.List;

public class ParticipantEntity {
    private String name;
    private boolean isMatched;
    private List<String> references;

    public ParticipantEntity() {
    }

    public ParticipantEntity(String name, boolean isMatched, List<String> references) {
        this.name = name;
        this.isMatched = isMatched;
        this.references = references;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMatched() {
        return this.isMatched;
    }

    public void setMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public List<String> getReferences() {
        return this.references;
    }

    public void setReferences(List<String> references) {
        this.references = new ArrayList<>(references);
    }

    public String toString() {
        return "{" +
                references +
                "}";
    }
}
