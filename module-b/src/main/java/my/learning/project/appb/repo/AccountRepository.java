package my.learning.project.appb.repo;

import my.learning.project.appb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
