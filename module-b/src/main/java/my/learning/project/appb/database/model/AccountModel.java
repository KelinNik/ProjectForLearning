package my.learning.project.appb.database.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountModel {

    @Column
    @Id
    @NotBlank
    private String accountId;
    @Column
    private String firstName;
    @Column
    private String secondName;
}
