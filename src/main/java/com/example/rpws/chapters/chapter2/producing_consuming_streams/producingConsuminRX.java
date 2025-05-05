package com.example.rpws.chapters.chapter2.producing_consuming_streams;


import rx.Observable;
import rx.Subscriber;

public class producingConsuminRX {

    public static void main(String[] args) {
        Observable<String> observableWithClassAnonymous = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, reactive world!");
                        sub.onCompleted();
                    }
                }
        );

        Observable<String> observableWithLambda = Observable.create(sub -> {
            int record = 1;
            while (record <= 10){
                sub.onNext("Hello, reactive world!: " + record );
                record ++;
            }


            sub.onCompleted();
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println(throwable);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observableWithLambda.subscribe(subscriber);
    }
}



