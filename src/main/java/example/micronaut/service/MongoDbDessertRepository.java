package example.micronaut.service;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import example.micronaut.configuration.MongoDbConfiguration;
import example.micronaut.entity.Dessert;
import example.micronaut.repository.DessertRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
public class MongoDbDessertRepository implements DessertRepository {

    private final MongoDbConfiguration mongoConfig;
    private final MongoClient mongoClient;

    public MongoDbDessertRepository(MongoDbConfiguration mongoConfig,
                                    MongoClient mongoClient) {
        this.mongoConfig = mongoConfig;
        this.mongoClient = mongoClient;
    }

    @Override
    @NonNull
    public Publisher<Dessert> list() {
        return getCollection().find();
    }

    @Override
    public Mono<Boolean> save(@NonNull @NotNull @Valid Dessert dessert) {
        return Mono.from(getCollection().insertOne(dessert))
                .map(insertOneResult -> true)
                .onErrorReturn(false);
    }

    @NonNull
    private MongoCollection<Dessert> getCollection() {
        return mongoClient.getDatabase(mongoConfig.getName())
                .getCollection(mongoConfig.getCollection(), Dessert.class);
    }
}
