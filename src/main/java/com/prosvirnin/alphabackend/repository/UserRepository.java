package com.prosvirnin.alphabackend.repository;

import com.prosvirnin.alphabackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFullName(String fullName);
}
