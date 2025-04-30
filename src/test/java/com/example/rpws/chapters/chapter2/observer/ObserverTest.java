package com.example.rpws.chapters.chapter2.observer;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;

@SpringBootTest
class ObserverTest {

    @Test
    void observersHandleEventsFromSubject() {

        // given
        Subject<String> producerSubject = new ConcreteSubject();
        Observer<String> observerA = Mockito.spy(new ConcreteObserverA());
        Observer<String> observerB = Mockito.spy(new ConcreteObserverB());

        // when
        producerSubject.notifyObservers("No listeners");

        producerSubject.registerObserver(observerA);
        producerSubject.notifyObservers("Message for A");

        producerSubject.registerObserver(observerB);
        producerSubject.notifyObservers("Message for A & B");

        producerSubject.notifyObservers("Message for B");

        producerSubject.unregisterObserver(observerB);
        producerSubject.notifyObservers("No listeners");

        // then
        Mockito.verify(observerA, times(1)).observe("Message for A");
        Mockito.verify(observerA, times(1)).observe("Message for A & B");
        Mockito.verifyNoMoreInteractions(observerA);
        Mockito.verify(observerB, times(1)).observe("Message for A & B");
        Mockito.verify(observerB, times(1)).observe("Message for B");
        Mockito.verifyNoMoreInteractions(observerB);

    }

    @Test
    public void subjectLeveragesLambdas() {
        Subject<String> subject = new ConcreteSubject();
        subject.registerObserver(e -> System.out.println("A: " + e));
        subject.registerObserver(e -> System.out.println("B: " + e));
        subject.notifyObservers("This message will receive A & B");
    }
}


