package guru.bytecode.rrp;

import java.util.Objects;
import java.util.Set;

class Move {
    
    private final Room room;
    private final Set<Item> collectedItems;

    Move(Room room, Set<Item> collectedItems) {
        Objects.requireNonNull(room);
        Objects.requireNonNull(collectedItems);
        this.room = room;
        this.collectedItems = collectedItems;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(room, collectedItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Move) {
            Move m = (Move) o;
            return Objects.equals(room, m.room)
                    && Objects.equals(collectedItems, m.collectedItems);
        }
        return false;
    }

}
