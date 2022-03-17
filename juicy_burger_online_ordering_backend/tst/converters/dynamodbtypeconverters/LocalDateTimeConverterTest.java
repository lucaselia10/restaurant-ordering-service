package converters.dynamodbtypeconverters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocalDateTimeConverterTest {
    private LocalDateTimeConverter localDateTimeConverter;

    @BeforeEach
    void setup() {
        localDateTimeConverter = new LocalDateTimeConverter();
    }

    @Test
    void convert_withLocalDateTime_returnsStringOfLocalDateTime() {

    }

    @Test
    void unconvert_withValidStringOfLocalDateTime_returnsLocalDateTime() {

    }

    @Test
    void unconvert_withInvalidStringOfLocalDateTime_throws() {

    }
}
