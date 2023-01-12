package lk.ijse.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingDemo2 {

    public static void main(String[] args) {
        log.trace("This is a trace message");
        log.debug("This is a debug message");
        log.info("This is an information message");
        log.warn("This is a warning message");
        log.error("This is an error message");
    }
}
