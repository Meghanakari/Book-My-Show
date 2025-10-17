package com.bms.dbapi.repository;

import com.bms.dbapi.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    @Query(value = "select * from users where user_type='SYSTEM_ADMIN'", nativeQuery = true)
    public List<AppUser> getAllAdmins();

    public AppUser findByEmail(String email);
}
