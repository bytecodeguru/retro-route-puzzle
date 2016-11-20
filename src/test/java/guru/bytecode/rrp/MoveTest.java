package guru.bytecode.rrp;

import java.util.Collections;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoveTest {

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Mock
    private Room room;

    @Test
    public void constructorPreconditions() {
        softly.assertThatThrownBy(() -> {
            Move move = new Move(null, Collections.emptySet());
        }).isInstanceOf(NullPointerException.class);

        softly.assertThatThrownBy(() -> {
            Move move = new Move(room, null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testEquals() {
        Move m1 = new Move(room, Collections.emptySet());
        Move m2 = new Move(room, Collections.emptySet());
        Move m3 = new Move(room, Collections.singleton(new Item()));
        Move m4 = new Move(mock(Room.class), Collections.emptySet());
        softly.assertThat(m1.equals(m1)).isTrue();
        softly.assertThat(m1.equals(null)).isFalse();
        softly.assertThat(m1.equals(m2)).isTrue();
        softly.assertThat(m1.equals(m3)).isFalse();
        softly.assertThat(m1.equals(m4)).isFalse();
    }
}
