package example.micronaut.repository;

import example.micronaut.entity.Dessert;
import io.micronaut.core.annotation.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface DessertRepository {

    @NonNull
    List<Dessert> list();

    void save(@NonNull @NotNull @Valid Dessert dessert);
}
