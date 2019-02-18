package register.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<Entity, Id> {

    Entity save(Entity entity);

    List<Entity> findAll();

    Optional<Entity> findById(Id id);

    void remove(String id);

    BigDecimal totalSalary();

    Double averageSalary();
}
