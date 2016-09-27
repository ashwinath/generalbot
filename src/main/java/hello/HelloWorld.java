package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ashwin on 27/9/2016.
 */
public class HelloWorld {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String... args) {
        LOGGER.info("Hello World");
    }
}
