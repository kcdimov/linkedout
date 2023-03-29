package bg.linkedout.models.dtos;

import bg.linkedout.models.entities.Company;
import bg.linkedout.models.entities.EducationLevel;
import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeAddDto {

    @NotBlank(message = "Please fill the field")
    @Length(min = 2, message = "First name must be minimum two characters")
    private String firstName;
    @NotBlank(message = "The Field cannot be empty, please fill the last name")
    @Length(min = 2, message = "Last name must be minimum two characters")
    private String lastName;
    @NotNull(message = "Please choose one of the education level")
    private EducationLevel educationLevel;
    @NotNull(message = "Please choose one of the companies")
    private String company;
    @NotNull(message = "The filed cannot be empty. Please enter job title")
    @Length(min = 3, message = "Job Title must be minimum three symbols")
    private String jobTitle;
    @NotNull(message = "Please enter valid birth date")
    @Past(message = "Birthdate cannot be in the future or present")
    @DateTimeFormat(pattern = "yyyy-MM-mm")
    private Date birthdate;
    @NotNull(message = "Salary cannot be null")
    @Min(value = 1, message = "Salary must positive number")
    private BigDecimal salary;


    public EmployeeAddDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
