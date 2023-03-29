package bg.linkedout.service;


import bg.linkedout.models.entities.Company;
import bg.linkedout.models.service.CompanyServiceModel;
import bg.linkedout.models.views.CompanyViewModel;

import java.util.List;


public interface CompanyService {
    public CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel);
    List<CompanyViewModel> findAllCompanies();
    CompanyServiceModel findById(String id);

    Company findCompanyByName(String name);
    void deleteCompanyById(String id);
}