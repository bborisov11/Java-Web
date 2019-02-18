package register.repository;

import register.domain.entities.Employee;

import java.util.Optional;

public interface EmployeeRepository extends GenericRepository<Employee, String> {

    Optional<Employee> findByName(String name);
}
