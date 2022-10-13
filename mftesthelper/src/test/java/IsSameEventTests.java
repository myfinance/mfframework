import static de.hf.myfinance.event.Event.Type.CREATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static de.hf.testhelper.IsSameEvent.sameEventExceptCreatedAt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.hf.myfinance.event.Event;
import de.hf.myfinance.restmodel.Instrument;
import de.hf.myfinance.restmodel.InstrumentType;
import org.junit.jupiter.api.Test;

class IsSameEventTests {

    ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Test
    void testEventObjectCompare() throws JsonProcessingException {

        var instrument1 = new Instrument("1", "name", InstrumentType.TENANT, true);
        var instrument2 = new Instrument("2", "name", InstrumentType.TENANT, true);

        // Event #1 and #2 are the same event, but occurs as different times
        // Event #3 is a different event
        Event<Integer, Instrument> event1 = new Event<>(CREATE, 1, instrument1);
        Event<Integer, Instrument> event2 = new Event<>(CREATE, 1, instrument1);
        Event<Integer, Instrument> event3 = new Event<>(CREATE, 1, instrument2);

        String event1Json = mapper.writeValueAsString(event1);

        assertThat(event1Json, is(sameEventExceptCreatedAt(event2)));
        assertThat(event1Json, not(sameEventExceptCreatedAt(event3)));
    }
}