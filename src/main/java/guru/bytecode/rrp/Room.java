package guru.bytecode.rrp;

import java.util.Set;

interface Room {

    Integer getId();
    
    String getName();
    
    Set<Item> getItems();

    Set<Room> getNeighbors();

}
