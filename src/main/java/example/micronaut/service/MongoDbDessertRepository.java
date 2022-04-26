package example.micronaut.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import example.micronaut.configuration.MongoDbConfiguration;
import example.micronaut.entity.Dessert;
import example.micronaut.repository.DessertRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    public List<Dessert> list() {
        return getCollection().find().into(new ArrayList<>());
    }

    @Override
    public void save(@NonNull @NotNull @Valid Dessert dessert) {
        getCollection().insertOne(dessert);
    }

    @NonNull
    private MongoCollection<Dessert> getCollection() {
        return mongoClient.getDatabase(mongoConfig.getName())
                .getCollection(mongoConfig.getCollection(), Dessert.class);
    }
}
