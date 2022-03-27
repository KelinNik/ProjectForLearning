package my.learning.project.appb.database.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountModel {

    @Column
    @Id
    private Long accountId;
    @Column
    private String firstName;
    @Column
    private String secondName;
}
