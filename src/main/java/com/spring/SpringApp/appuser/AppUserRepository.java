package com.spring.SpringApp.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional()
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
   Optional<AppUser> findByEmail(String email);

}
