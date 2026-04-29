package com.cinereserve.repository;

import com.cinereserve.entity.ShowSeat;
import com.cinereserve.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findByShowIdAndIdIn(Long showId, List<Long> seatIds);

    List<ShowSeat> findByStatusAndLastModifiedBefore(SeatStatus status, LocalDateTime cutoff);
}