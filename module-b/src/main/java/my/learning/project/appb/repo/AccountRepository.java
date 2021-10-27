package my.learning.project.appb.repo;

import my.learning.project.appb.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, String> {

    @Query(value = "SELECT * FROM account LIMIT 1", nativeQuery = true)
    Account getAccounts();
}
