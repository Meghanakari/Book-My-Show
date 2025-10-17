package com.bms.dbapi.repository;

import com.bms.dbapi.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Repository
public interface HallRepository extends JpaRepository<Hall, UUID> {
}
