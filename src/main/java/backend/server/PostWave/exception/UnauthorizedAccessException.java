package backend.server.PostWave.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String message) {

      super(message);
    }
}
