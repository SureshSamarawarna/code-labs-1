package lk.ijse.dep9;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Challenge 1: Make it run, but you can't change the code inside the main()
 * Challenge 2: Write a log inside the catch block
 * Challenge 3: Change the log level to display trace messages
 * Challenge 4: Save logs to a file
 */

@Slf4j
public class LoggingDemo {

    //private static final k log = LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        log.trace("This is a trace message");
        log.debug("This is a debug message");
        log.info("This is an information message");
        log.warn("This is a warning message");
        log.error("This is an error message");

        String value = "12a";
        try {
            int i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            /* Handle the exception here */
            /* Exception message should be "value is not a valid number" */
            log.error("{} is not a valid number", value, e);
        }
    }

}
