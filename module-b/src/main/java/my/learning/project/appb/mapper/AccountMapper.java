package my.learning.project.appb.mapper;

import my.learning.project.appb.dto.AccountDto;
import my.learning.project.appb.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);
    AccountDto mapToDto(Account account);
    Account mapToAccount(AccountDto accountDto);
}
