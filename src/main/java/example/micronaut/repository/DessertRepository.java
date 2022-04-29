package example.micronaut.repository;

import example.micronaut.entity.Dessert;
import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface DessertRepository {

    @NonNull
    Publisher<Dessert> list();

    Mono<Boolean> save(@NonNull @NotNull @Valid Dessert dessert);
}
