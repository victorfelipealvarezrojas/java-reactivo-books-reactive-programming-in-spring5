package com.example.rpws.chapters.chapter3.inconsistencyproblem;

import org.springframework.util.concurrent.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@SuppressWarnings("removal")
public final class AsyncAdapters {


    public static <T> CompletionStage<T> toCompletion(ListenableFuture<T> future) {

        CompletableFuture<T> completableFuture = new CompletableFuture<>();

        future.addCallback(
                completableFuture::complete,
                completableFuture::completeExceptionally
        );

        return completableFuture;
    }

    public static <T> ListenableFuture<T> toListenable(CompletionStage<T> stage) {
        SettableListenableFuture<T> future = new SettableListenableFuture<>();

        stage.whenComplete((v, t) -> {
            if (t == null) {
                future.set(v);
            } else {
                future.setException(t);
            }
        });

        return future;
    }

}
