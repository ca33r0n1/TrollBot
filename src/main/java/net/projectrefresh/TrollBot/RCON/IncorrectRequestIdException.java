package net.projectrefresh.TrollBot.RCON;

public class IncorrectRequestIdException extends AuthenticationException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new exception with the specified request id.
     *
     * @param requestId
     *            The request id.
     */
    public IncorrectRequestIdException(final int requestId) {
        super("Request id:" + requestId);
    }

    /**
     * Construct a new exception with the specified request id and cause.
     *
     * @param requestId
     *            The request id.
     * @param cause
     *            The original cause of this exception.
     */
    public IncorrectRequestIdException(final int requestId, final Throwable cause) {
        super("Request id:" + requestId, cause);
    }

}