package com.example.rpws.chapters.chapter2.GeneratingAsynchronousSequence;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncSec {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch externalSignal = new CountDownLatch(1);
        Subscription subscription = myObs().subscribe(subscriber); // se ejecuta en un hilo independiente
        externalSignal.await();
        subscription.unsubscribe();
    }

    public static Observable<Long> myObs() throws InterruptedException {
        return Observable
                .interval(100, TimeUnit.SECONDS);
    }

    public static Subscriber<Long> subscriber = new Subscriber<Long>() {
        @Override
        public void onCompleted() {
            System.out.println("Done!");
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(throwable);
        }

        @Override
        public void onNext(Long s) {
            System.out.println("received: " + s);
        }
    };
}
