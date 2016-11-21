package guru.bytecode.rrp;

import java.util.Set;

interface Room {

    Set<Item> getItems();

    Set<Room> getNeighbors();

}
