package guru.bytecode.rrp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

class JsonMap {

    private final List<JsonRoom> rooms;

    @JsonCreator
    JsonMap(@JsonProperty("rooms") List<JsonRoom> rooms) {
        this.rooms = Objects.requireNonNull(rooms);
    }

    List<JsonRoom> getRooms() {
        return rooms;
    }

}
