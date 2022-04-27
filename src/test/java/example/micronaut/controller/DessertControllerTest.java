package example.micronaut.controller;


import example.micronaut.DessertClient;
import example.micronaut.MongoDbUtils;
import example.micronaut.entity.Dessert;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MicronautTest
@TestInstance(PER_CLASS)
public class DessertControllerTest implements TestPropertyProvider {

    @Inject
    DessertClient dessertClient;

    @Test
    void dessertEndpointInteractsWithMongo() {
        List<Dessert> desserts = dessertClient.findAll();
        assertTrue(desserts.isEmpty());

        HttpStatus status = dessertClient.save(new Dessert("ice-cream"));
        assertEquals(CREATED, status);

        desserts = dessertClient.findAll();
        assertFalse(desserts.isEmpty());
        assertEquals("ice-cream", desserts.get(0).getName());
        assertNull(desserts.get(0).getDescription());

        status = dessertClient.save(new Dessert("pavlova", "meringue, fruit & ice-cream"));
        assertEquals(CREATED, status);

        desserts = dessertClient.findAll();
        assertEquals(2, desserts.size());
    }

    @AfterAll
    static void cleanup() {
        MongoDbUtils.closeMongoDb();
    }

    @Override
    public @NotNull Map<String, String> getProperties() {
        MongoDbUtils.startMongoDb();
        return Collections.singletonMap("mongodb.uri", MongoDbUtils.getMongoDbUri());
    }
}