package main.stable.matching.core.model;

import main.stable.matching.core.StableMatching;

public class ProviderParticipant extends Participant {

    public ProviderParticipant(StableMatching matching) {
        super(matching);
    }

    @Override
    public StableMatching build() {
        this.stableMatching.getStableMatchEntity().setProviders(this.participants);
        return this.stableMatching;
    }
}
