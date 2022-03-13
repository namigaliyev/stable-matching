## Introduction

The stable matching algorithm provided by Gale and Shapley for that of matching two sets of agents in such a manner that no two unmatched agents prefer each other to their mates.

## Example 

The following example shows that participants can be imported from a file or object list.

```
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
```

## Citations

1. Gale, D., & Sotomayor, M. (1985). [Some remarks on the stable matching problem](https://www.sciencedirect.com/science/article/pii/0166218X85900745). Discrete Applied Mathematics, 11(3), 223-232.
2. Golle, P. (2006, February). [A private stable matching algorithm](https://link.springer.com/chapter/10.1007/11889663_5). In International Conference on Financial Cryptography and Data Security (pp. 65-80). Springer, Berlin, Heidelberg.