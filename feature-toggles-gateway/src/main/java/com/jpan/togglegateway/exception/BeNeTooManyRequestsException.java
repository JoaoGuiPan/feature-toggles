package com.jpan.togglegateway.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

public class BeNeTooManyRequestsException extends HttpStatusCodeException {

    private final int retryAfter;

    public BeNeTooManyRequestsException(final int retryAfter) {
        super(
                TOO_MANY_REQUESTS,
                TOO_MANY_REQUESTS.getReasonPhrase(),
                buildRetryAfterHeader(retryAfter),
                "Too Many Requests to BeNe!".getBytes(),
                StandardCharsets.UTF_8
        );
        this.retryAfter = retryAfter;
    }

    private static HttpHeaders buildRetryAfterHeader(int retryAfter) {
        return new HttpHeaders(
                new LinkedMultiValueMap<>(
                        Map.of(
                                HttpHeaders.RETRY_AFTER, List.of(
                                        String.valueOf(retryAfter)
                                )
                        )
                )
        );
    }

    public int getRetryAfter() {
        return retryAfter;
    }
}
