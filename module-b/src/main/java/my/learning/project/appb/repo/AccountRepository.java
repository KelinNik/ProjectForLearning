package my.learning.project.appb.repo;

import my.learning.project.appb.database.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, String> {

    List<AccountModel> findAllBy();

}
