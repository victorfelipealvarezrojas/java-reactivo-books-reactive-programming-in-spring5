package com.example.rpws.chapters.chapter2.producing_consuming_streams;


import rx.Observable;
import rx.Subscriber;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class producingConsuminRX {

    public static void main(String[] args) {
        /*
         * Observable: Representa una fuente de datos reactiva que emite eventos.
         * Esta implementación utiliza una clase anónima que implementa la interfaz
         * Observable.OnSubscribe para definir el comportamiento cuando un
         * Subscriber se suscriba.
         */
        Observable<String> observableWithClassAnonymous = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Esto se emitirá apenas aparezca el subscriptor!");
                        sub.onCompleted();
                    }
                }
        );

        /*
         * Observable implementado con expresiones lambda (Java 8+).
         * Esta implementación es equivalente a la anterior pero con sintaxis más concisa.
         * Emitirá 10 elementos en secuencia y luego se completará.
         */
        Observable<String> observableWithLambda = Observable.create(sub -> {
            int record = 1;
            while (record <= 10) {
                sub.onNext("Esto se emitirá apenas aparezca el subscriptor!: " + record);
                record++;
            }
            sub.onCompleted();
        });

        /*
         * Subscriber: Define cómo reaccionar a los eventos emitidos por el Observable.
         * Implementa tres métodos principales que corresponden al patrón de programación reactiva:
         * - onNext: Maneja cada elemento emitido por el Observable
         * - onError: Maneja cualquier error que ocurra durante el proceso
         * - onCompleted: Se ejecuta cuando la secuencia termina normalmente
         */
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

        /*
         * Suscripción: Este es el punto donde se establece la conexión entre
         * el Observable y el Subscriber. Al suscribirse, el Observable comenzará
         * a emitir eventos que serán procesados por el Subscriber.
         */
        observableWithLambda.subscribe(subscriber);

        //otros ejemplos transformado listas típicas

        Observable.just("1", "2", "3", "4");
        Observable.from(new String[]{"A", "B", "C"});
        Observable.from(Collections.emptyList());

        Observable<String> hello = Observable.fromCallable(() -> "Hello ");
        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.from(future);

        Observable.concat(hello, world, Observable.just("!")).forEach(System.out::print);

    }
}



