package com.example.rpws.chapters.pullpushmodel;

import org.reactivestreams.Publisher;

public interface AsyncDatabaseClient {
    Publisher<Item> getStreamOfItems();
}
