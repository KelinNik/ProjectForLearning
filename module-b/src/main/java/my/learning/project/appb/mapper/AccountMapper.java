package my.learning.project.appb.mapper;

import my.learning.project.appb.database.model.AccountModel;
import my.learning.project.appb.dto.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account mapToAccount(AccountModel account);

    AccountModel mapToModel(Account account);
}
