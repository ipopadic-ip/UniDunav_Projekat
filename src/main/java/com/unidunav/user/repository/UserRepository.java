package com.unidunav.user.repository;

import com.unidunav.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u JOIN u.roles r WHERE u.email = :email AND u.deleted = false AND r.aktivna = true")
    Optional<User> findActiveUserWithActiveRolesByEmail(@Param("email") String email);

    

    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> findAllActive();
    
    @Query("SELECT u FROM User u WHERE u.deleted = true")
    List<User> findAllDeleted();
    
    @Query("SELECT u FROM User u ORDER BY u.deleted ASC, u.prezime ASC, u.ime ASC")
    List<User> findAllOrderedByStatusAndName();

}
