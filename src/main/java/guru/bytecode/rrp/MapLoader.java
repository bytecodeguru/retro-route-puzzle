package guru.bytecode.rrp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

class MapLoader {

    private final ObjectMapper jackson;

    MapLoader() {
        jackson = new ObjectMapper();
    }

    Map<Integer, ? extends Room> loadFrom(File f) throws IOException {
        JsonMap jsonMap = jackson.readValue(f, JsonMap.class);
        Map<Integer, JsonRoom> roomMap = mapRooms(jsonMap);
        updateNeighbors(roomMap);
        return roomMap;
    }

    private void updateNeighbors(Map<Integer, JsonRoom> roomMap) {
        roomMap.values().stream().forEach(r -> {
            r.updateNeighbors(roomMap);
        });
    }

    private static Map<Integer, JsonRoom> mapRooms(JsonMap jsonMap) {
        return jsonMap.getRooms().stream()
                .collect(toMap(
                        JsonRoom::getId,
                        identity()
                ));
    }

}
