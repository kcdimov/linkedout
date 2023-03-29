package bg.linkedout.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class CompanyAddDto {

    @NotNull(message = "The field cannot be empty, please enter name")
    @Length(min = 2, max = 10, message = "The name must be between two and ten characters")
    private String name;
    @NotNull(message = "The field cannot be empty, please enter town")
    @Length(min = 2, max = 10, message = "The town must be between two and ten characters")
    private String town;
    @NotNull(message = "The field cannot be empty")
    @Length(min = 10, message = "The Description must be minimum ten characters")
    private String description;
    @NotNull(message = "The field budget cannot be empty")
    @Min(value = 0, message = "Please enter positive number")
    private BigDecimal budget;

    public CompanyAddDto() {
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
