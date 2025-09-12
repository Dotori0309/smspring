package edu.sm.weather;

import edu.sm.util.WeatherUtil;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeatherTests {

    private static final Logger log = LoggerFactory.getLogger(WeatherTests.class);

    @Value("${weather.key}")
    private String key;

    @Test
    void contextLoads() throws IOException, ParseException {
        String loc = "108";
        Object object = WeatherUtil.getWeather(loc, key);
        log.info("{}", object);
        assertNotNull(object);
    }
}
