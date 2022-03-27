package my.learning.project.appb.repo;

import my.learning.project.appb.database.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

    @Query(value = "SELECT * FROM account LIMIT 1", nativeQuery = true)
    AccountModel getAccounts();
}
