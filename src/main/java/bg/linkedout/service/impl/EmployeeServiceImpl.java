package bg.linkedout.service.impl;

import bg.linkedout.models.dtos.EmployeeAddDto;
import bg.linkedout.models.entities.Company;
import bg.linkedout.models.entities.Employee;
import bg.linkedout.models.service.EmployeeServiceModel;
import bg.linkedout.models.views.EmployeeViewModel;
import bg.linkedout.repository.EmployeeRepository;
import bg.linkedout.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final CompanyServiceImpl companyService;

    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository, CompanyServiceImpl companyService) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }


    @Override
    public void addEmployee(EmployeeServiceModel employeeServiceModel, @Valid EmployeeAddDto employeeAddDto) {
        Employee employee = modelMapper.map(employeeServiceModel, Employee.class);

        String companyName = employeeAddDto.getCompany();
        Company companyByName = this.companyService.findCompanyByName(companyName);
        employee.setCompany(companyByName);

        this.employeeRepository.saveAndFlush(employee);
    }

    public List<EmployeeViewModel> findAll() {
        return this.employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    public EmployeeServiceModel findById(String id) {

        Employee employee = this.employeeRepository.findById(id).orElse(null);

        EmployeeServiceModel employeeServiceModel = this.modelMapper.map(employee, EmployeeServiceModel.class);

        if (employee != null) {
            return employeeServiceModel;
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmployeeById(String id) {
        this.employeeRepository.deleteById(id);
    }

}
