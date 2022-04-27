package example.micronaut;

import example.micronaut.entity.Dessert;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client("/desserts")
public interface DessertClient {

    @Post
    @NonNull
    HttpStatus save(@NonNull @NotNull @Valid Dessert dessert);

    @NonNull
    @Get
    List<Dessert> findAll();
}
