package example.micronaut.entity;

import io.micronaut.core.annotation.*;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotBlank;

@Introspected
@ReflectiveAccess       //annotation fixes codec error in native image
public class Dessert {

    @NonNull
    @NotBlank
    @BsonProperty("name")
    private final String name;

    @Nullable
    @BsonProperty("description")
    private final String description;

    public Dessert(@NonNull String name) {
        this(name, null);
    }

    @Creator
    @BsonCreator
    public Dessert(@NonNull @BsonProperty("name") String name,
                   @Nullable  @BsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}
