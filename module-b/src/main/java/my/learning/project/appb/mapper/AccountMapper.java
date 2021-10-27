package my.learning.project.appb.mapper;

import my.learning.project.appb.database.model.AccountModel;
import my.learning.project.appb.dto.Account;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AccountMapper {

    Account mapToAccount(AccountModel account);

    AccountModel mapToModel(Account account);
}
