package guru.bytecode.rrp;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

class Quester {

    Optional<Route> findRoute(Room startingRoom, Set<Item> itemsToCollect) {
        Objects.requireNonNull(startingRoom);
        Objects.requireNonNull(itemsToCollect);
        return Optional.empty();
    }

}
