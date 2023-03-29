package bg.linkedout.service.impl;

import bg.linkedout.models.entities.Company;
import bg.linkedout.models.service.CompanyServiceModel;
import bg.linkedout.models.views.CompanyViewModel;
import bg.linkedout.repository.CompanyRepository;
import bg.linkedout.service.CompanyService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }


    @Override
    public CompanyServiceModel addCompany(CompanyServiceModel companyServiceModel) {

        Company company = this.modelMapper.map(companyServiceModel, Company.class);
        this.companyRepository.saveAndFlush(company);

        return companyServiceModel;
    }

    @Override
    public List<CompanyViewModel> findAllCompanies() {
        return this.companyRepository.findAll()
                .stream()
                .map(company-> this.modelMapper.map(company, CompanyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyServiceModel findById(String id) {
        Company company = this.companyRepository.findById(id).orElse(null);
        CompanyServiceModel companyServiceModel = this.modelMapper.map(company, CompanyServiceModel.class);
        if (company != null) {
            return companyServiceModel;
        } else {
            return null;
        }
    }

    @Override
    public Company findCompanyByName(String name) {

        return this.companyRepository.findCompanyByName(name);
    }

    @Override
    public void deleteCompanyById(String id) {
        this.companyRepository.deleteById(id);
    }
}
