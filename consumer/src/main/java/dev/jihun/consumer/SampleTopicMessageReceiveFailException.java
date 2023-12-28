package dev.jihun.consumer;

public class SampleTopicMessageReceiveFailException extends RuntimeException {

    public SampleTopicMessageReceiveFailException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
