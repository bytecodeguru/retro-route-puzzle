package guru.bytecode.rrp;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Quester {

    List<Move> findRoute(Room startingRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startingRoom);
        Objects.requireNonNull(itemsToCollect);
        return Collections.singletonList(new Move());
    }

}
