package main.stable.matching.core.model;

import main.stable.matching.core.StableMatching;

public class ClientParticipant extends Participant {

    public ClientParticipant(StableMatching stableMatching) {
        super(stableMatching);
    }

    @Override
    public StableMatching build() {
        this.stableMatching.getStableMatchEntity().setClients(this.participants);
        return this.stableMatching;
    }
}
