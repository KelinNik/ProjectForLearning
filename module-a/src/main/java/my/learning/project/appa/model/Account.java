package my.learning.project.appa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@NoArgsConstructor
public class Account {

    private String accountId;
    private String firstName;
    private String secondName;
}
