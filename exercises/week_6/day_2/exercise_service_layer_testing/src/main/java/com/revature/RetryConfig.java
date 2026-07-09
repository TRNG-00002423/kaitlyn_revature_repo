package com.revature;

/**
 * RetryConfig
 */
public interface RetryConfig {

    int getMaxAttempts();

    long getRetryDelayMs();

}
