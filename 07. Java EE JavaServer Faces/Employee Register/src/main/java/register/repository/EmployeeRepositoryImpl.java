package register.repository;

import register.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager;
    @Inject
    public EmployeeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("registerUI")
                .createEntityManager();
    }

    @Override
    public Optional<Employee> findByName(String firstName) {
        try {
            return Optional.of(this.entityManager
                    .createQuery("SELECT e FROM Employee e WHERE e.firstName = :firstName", Employee.class)
                    .setParameter("firstName", firstName)
                    .getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public Employee save(Employee employee) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(employee);
        this.entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager
                .createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }

    @Override
    public Optional<Employee> findById(String id) {
        try {
            return Optional.of(this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public void remove(String id) {
        this.entityManager.getTransaction().begin();
            this.entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :id")
            .setParameter("id", id)
            .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public BigDecimal totalSalary() {
        return (BigDecimal) this.entityManager
                .createQuery("SELECT sum(e.salary) FROM Employee e")
                .getSingleResult();
    }

    @Override
    public Double averageSalary() {
        return (Double) this.entityManager
                .createQuery("SELECT avg(e.salary) FROM Employee e")
                .getSingleResult();
    }
}
