package guru.bytecode.rrp;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(Integer roomId) {
        super(roomId.toString());
    }
    
}
