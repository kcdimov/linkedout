package bg.linkedout.repository;

import bg.linkedout.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    Company findCompanyByName(String companyName);

}
