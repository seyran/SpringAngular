package com.seyrancom.consulting.app.repository.dao.impl;

import com.seyrancom.consulting.app.domain.entity.Employee;
import com.seyrancom.consulting.app.repository.dao.UserDAO;
import com.seyrancom.consulting.core.repository.dao.common.AbstractJpaDAO;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl extends AbstractJpaDAO<Employee> implements UserDAO {
}