package bg.linkedout.web;

import bg.linkedout.models.dtos.EmployeeAddDto;
import bg.linkedout.models.service.EmployeeServiceModel;
import bg.linkedout.service.impl.CompanyServiceImpl;
import bg.linkedout.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final ModelMapper modelMapper;
    private final EmployeeServiceImpl employeeService;
    private final CompanyServiceImpl companyService;


    public EmployeeController(ModelMapper modelMapper, EmployeeServiceImpl employeeService, CompanyServiceImpl companyService) {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @ModelAttribute
    public EmployeeAddDto employeeAddDto() {
        return new EmployeeAddDto();
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {

        if (!model.containsAttribute("employeeAddDto")) {
            model.addAttribute("employeeAddDto", new EmployeeAddDto());
        }

        model.addAttribute("companies",this.companyService.findAllCompanies());

        return "employee-add";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeAddDto employeeAddDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("employeeAddDto", employeeAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeAddDto",
                    bindingResult);

            return "redirect:/employees/add";
        }



        this.employeeService.addEmployee(modelMapper.map(employeeAddDto, EmployeeServiceModel.class), employeeAddDto);
        return "redirect:/employee-all";
    }

    @GetMapping("/all")
    public String getAllEmployees(Model model) {

        model.addAttribute("allEmployees", this.employeeService.findAll());

        return "employee-all";
    }

    @GetMapping("/employee-details/{id}")
    public String employeeDetails(@PathVariable("id") String id, Model model) {
        EmployeeServiceModel employeeServiceModel = this.employeeService.findById(id);

        model.addAttribute("employee", employeeServiceModel);
        return "/employee-details";
    }

    @DeleteMapping("/employee-details/{id}")
    public String deleteEmployeeById(@PathVariable("id") String id) {

        this.employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }
}
