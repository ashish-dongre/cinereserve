package com.cinereserve.service;

import com.cinereserve.dto.WaitlistRequest;
import com.cinereserve.entity.Show;
import com.cinereserve.entity.User;
import com.cinereserve.entity.Waitlist;
import com.cinereserve.repository.ShowRepository;
import com.cinereserve.repository.UserRepository;
import com.cinereserve.repository.WaitlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WaitlistService {

    @Autowired
    private WaitlistRepository waitlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    public Waitlist joinWaitlist(WaitlistRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Waitlist waitlist = Waitlist.builder()
                .user(user)
                .show(show)
                .requestedSeats(request.getRequestedSeats())
                .requestTime(LocalDateTime.now())
                .status("PENDING")
                .build();

        return waitlistRepository.save(waitlist);
    }
}
