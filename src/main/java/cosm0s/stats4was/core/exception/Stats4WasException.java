package cosm0s.stats4was.core.exception;


import cosm0s.stats4was.log.L4j;

public class Stats4WasException extends Exception {

    public Stats4WasException(String message, Throwable cause) {
        L4j.getL4j().error(message, cause);
    }

    public Stats4WasException(String message) {
        L4j.getL4j().error(message);
    }

    public Stats4WasException(Throwable cause) {
        L4j.getL4j().error("UNKNOWN ERROR", cause);
    }
}
