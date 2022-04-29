package example.micronaut.controller;

import example.micronaut.entity.Dessert;
import example.micronaut.repository.DessertRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static io.micronaut.http.HttpStatus.CONFLICT;
import static io.micronaut.http.HttpStatus.CREATED;

@Controller("/desserts")
@ExecuteOn(TaskExecutors.IO)
public class DessertController {

    private final DessertRepository dessertRepository;


    public DessertController(DessertRepository dessertRepository) {
        this.dessertRepository = dessertRepository;
    }

    @Get
    Publisher<Dessert> dessertList() {
        return dessertRepository.list();
    }

    @Post
    Mono<HttpStatus> save(@NonNull @NotNull @Valid Dessert dessert) {
        return dessertRepository.save(dessert).map(added -> added ? CREATED : CONFLICT);
    }
}
