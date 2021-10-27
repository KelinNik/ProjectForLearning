package my.learning.project.appb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Component
@Validated
@NoArgsConstructor
public class Account {

    @Valid
    @NotEmpty
    private String accountId;
    private String firstName;
    private String secondName;
}
