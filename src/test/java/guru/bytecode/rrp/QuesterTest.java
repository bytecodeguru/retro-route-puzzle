package guru.bytecode.rrp;

import java.util.Collections;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuesterTest {

    private final Quester player1 = new Quester();

    @Mock
    private Room room;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void testFindRoutePreconditions() {
        softly.assertThatThrownBy(() -> 
                player1.findRoute(null, null)
        ).isInstanceOf(NullPointerException.class);
        softly.assertThatThrownBy(() -> 
                player1.findRoute(room, null)
        ).isInstanceOf(NullPointerException.class);
        softly.assertThatThrownBy(() -> 
                player1.findRoute(null, Collections.emptySet())
        ).isInstanceOf(NullPointerException.class);
    }

}
