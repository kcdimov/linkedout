package bg.linkedout.web;

import bg.linkedout.models.dtos.CompanyAddDto;
import bg.linkedout.models.service.CompanyServiceModel;
import bg.linkedout.service.impl.CompanyServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final ModelMapper modelMapper;
    private final CompanyServiceImpl companyService;

    public CompanyController(ModelMapper modelMapper, CompanyServiceImpl companyService) {
        this.modelMapper = modelMapper;
        this.companyService = companyService;

    }

    @ModelAttribute
    public CompanyAddDto companyAddDto() {
        return new CompanyAddDto();
    }

    @GetMapping("/add")
    public String addCompany(Model model) {

        if (!model.containsAttribute("companyAddDto")) {
            model.addAttribute("companyAddDto", new CompanyAddDto());
        }

        return "company-add";
    }

    @PostMapping("/add")
    public String addCompany(@Valid CompanyAddDto companyAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyAddDto", companyAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyAddDto",
                    bindingResult);

            return "redirect:/companies/add";
        }
        this.companyService.addCompany(this.modelMapper.map(companyAddDto, CompanyServiceModel.class));

        return "redirect:/companies/all";
    }

    @GetMapping("/all")
    public String allCompanies(Model model) {

        model.addAttribute("companies", this.companyService.findAllCompanies());

        return "company-all";
    }

    @GetMapping("/company-details/{id}")
    public String companyDetails(@PathVariable("id") String id, Model model) {
        CompanyServiceModel companyServiceModel = this.companyService.findById(id);

        model.addAttribute("company", companyServiceModel);
        return "/company-details";
    }

    @DeleteMapping("/company-details/{id}")
    public String deleteCompanyById(@PathVariable("id") String id){
        this.companyService.deleteCompanyById(id);

        return "redirect:/";
    }
}


