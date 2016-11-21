package guru.bytecode.rrp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

class JsonItem implements Item {

    private final String name;

    @JsonCreator
    JsonItem(@JsonProperty(value = "name") String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof JsonItem) {
            JsonItem i = (JsonItem) o;
            return Objects.equals(name, i.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
    
    

}
