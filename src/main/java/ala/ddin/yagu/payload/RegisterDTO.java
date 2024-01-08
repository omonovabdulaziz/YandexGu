package ala.ddin.yagu.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    @NotNull(message = "name kirit")
    private String name;
    @NotNull(message = "surname kirit")
    private String surname;
}
