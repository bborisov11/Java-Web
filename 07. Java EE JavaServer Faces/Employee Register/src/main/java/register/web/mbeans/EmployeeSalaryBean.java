package register.web.mbeans;

import register.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class EmployeeSalaryBean {

    private EmployeeService employeeService;

    public EmployeeSalaryBean() {
    }

    @Inject
    public EmployeeSalaryBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public BigDecimal totalSalary() {
        return this.employeeService.totalSalary();
    }

    public Double average() {
        return this.employeeService.average();
    }
}
