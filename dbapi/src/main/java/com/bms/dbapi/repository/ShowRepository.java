package com.bms.dbapi.repository;

import com.bms.dbapi.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShowRepository extends JpaRepository<Show, UUID> {
    @Query(value = "select * from shows where hall_id=:hallId", nativeQuery = true)
    public List<Show> getShowsByHallId(UUID hallId);
}
