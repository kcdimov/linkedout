package bg.linkedout.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(precision = 19, scale = 2)
    @Min(value = 0)
    private BigDecimal budget;
    @Column(columnDefinition = "TEXT")
    @Length(min = 10)
    private String description;
    @Column(unique = true)
    @NotNull
    @Length(min = 2, max = 10)
    private String name;
    @Column
    @NotNull
    @Length(min = 2, max = 10)
    private String town;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Company() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
