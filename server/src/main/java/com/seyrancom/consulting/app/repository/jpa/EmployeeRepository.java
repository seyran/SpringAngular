package com.seyrancom.consulting.app.repository.jpa;

import com.seyrancom.consulting.app.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.QueryHint;
import java.util.List;

@RepositoryRestResource
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    @Query(value = "from Employee")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Employee> findAllEmployees();
}