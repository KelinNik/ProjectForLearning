package my.learning.project.appb.repo;

import my.learning.project.appb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM account LIMIT 1", nativeQuery = true)
    Account getAccounts();
}
