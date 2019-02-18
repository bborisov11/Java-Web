package register.service;

import register.domain.models.service.EmployeeServiceModel;
import register.domain.models.view.EmployeeListViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {

    boolean saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    boolean removeEmployee(String id);

    BigDecimal totalSalary();

    Double average();
}
