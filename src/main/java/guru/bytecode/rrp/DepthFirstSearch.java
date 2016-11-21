package guru.bytecode.rrp;

import java.util.Deque;
import java.util.Set;

class DepthFirstSearch extends Search {
    
    @Override
    protected void addRoomsToVisit(Set<Room> neighbors, Deque<Room> roomsToVisit) {
        neighbors.forEach(roomsToVisit::offerFirst);
    }
    
}
