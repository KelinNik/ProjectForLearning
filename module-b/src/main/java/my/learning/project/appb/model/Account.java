package my.learning.project.appb.model;


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
public class Account {

    @Column
    @Id
    String accountId;
    @Column
    @NotBlank
    String firstName;
    @Column
    String secondName;
}
