package com.bms.dbapi.repository;

import com.bms.dbapi.models.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, UUID> {

    @Query(value = "select * from bookedseats where seat_number=:seat and show_id=:showId", nativeQuery = true)
    public BookedSeat getBookedSeat(UUID showId, int seat);
}
