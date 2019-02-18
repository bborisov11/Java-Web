package register.service;

import register.domain.entities.Employee;
import register.domain.models.service.EmployeeServiceModel;
import register.repository.EmployeeRepository;
import register.util.ModelMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveEmployee(EmployeeServiceModel employeeServiceModel) {
        try {
            this.employeeRepository.save(this.modelMapper.map(employeeServiceModel, Employee.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {

        return this.employeeRepository.findAll()
                .stream().map(e -> this.modelMapper.map(e, EmployeeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeEmployee(String id) {
        try {
            this.employeeRepository.remove(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public BigDecimal totalSalary() {
        return this.employeeRepository.totalSalary();
    }

    @Override
    public Double average() {
        return this.employeeRepository.averageSalary();
    }

}
