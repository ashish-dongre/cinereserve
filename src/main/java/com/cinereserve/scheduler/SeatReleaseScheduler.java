package com.cinereserve.scheduler;

import com.cinereserve.entity.ShowSeat;
import com.cinereserve.enums.SeatStatus;
import com.cinereserve.repository.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatReleaseScheduler {

    private final ShowSeatRepository showSeatRepository;

    @Scheduled(fixedRate = 60000)
    public void releaseExpiredSeats() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(5);
        List<ShowSeat> lockedSeats = showSeatRepository.findByStatusAndLastModifiedBefore(SeatStatus.LOCKED, cutoff);

        for (ShowSeat seat : lockedSeats) {
            seat.setStatus(SeatStatus.AVAILABLE);
        }
        showSeatRepository.saveAll(lockedSeats);
    }
}