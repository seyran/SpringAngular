package com.seyrancom.consulting.app.repository.jpa;

import com.seyrancom.consulting.app.domain.entity.User;
import com.seyrancom.consulting.app.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    /** Find persons by address.
     @Query(FIND_BY_ADDRESS_QUERY) public List<Person> findByAddress(@Param("address") String address);
     */

    /**
     * Find persons by address.
     */
    @Query("SELECT u FROM User u WHERE u.role = :role")
    public  List<User> findByRole(@Param("role") UserRole role);
}
