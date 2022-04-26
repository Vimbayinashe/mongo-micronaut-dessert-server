package example.micronaut.controller;

import example.micronaut.entity.Dessert;
import example.micronaut.repository.DessertRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static io.micronaut.http.HttpStatus.CREATED;

@Controller("/desserts")
@ExecuteOn(TaskExecutors.IO)
public class DessertController {

    private final DessertRepository dessertService;


    public DessertController(DessertRepository dessertService) {
        this.dessertService = dessertService;
    }

    @Get
    List<Dessert> dessertList() {
        return dessertService.list();
    }

    @Post
    @Status(CREATED)
    void save(@NonNull @NotNull @Valid Dessert dessert) {
        dessertService.save(dessert);
    }
}
