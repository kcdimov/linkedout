package bg.linkedout.service;

import bg.linkedout.models.dtos.EmployeeAddDto;
import bg.linkedout.models.service.EmployeeServiceModel;
import jakarta.validation.Valid;

public interface EmployeeService {

//    public EmployeeServiceModel addEmployee();

    void addEmployee(EmployeeServiceModel employeeServiceModel, @Valid EmployeeAddDto employeeAddDto);

    EmployeeServiceModel findById(String id);

    void deleteEmployeeById(String id);

}
