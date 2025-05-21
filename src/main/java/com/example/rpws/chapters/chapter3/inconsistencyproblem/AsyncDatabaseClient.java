package com.example.rpws.chapters.chapter3.inconsistencyproblem;

import java.util.concurrent.CompletionStage;

/**
 * This interface represents an asynchronous database client.
 * It provides a method to store data asynchronously.
 */
public interface AsyncDatabaseClient {
    <T> CompletionStage<T> store(CompletionStage<T> stage);
}
