package com.cinereserve.controller;

import com.cinereserve.dto.WaitlistRequest;
import com.cinereserve.entity.Waitlist;
import com.cinereserve.service.WaitlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistController {

    @Autowired
    private WaitlistService waitlistService;

    @PostMapping("/join")
    public ResponseEntity<Waitlist> joinWaitlist(@RequestBody WaitlistRequest request) {
        Waitlist waitlist = waitlistService.joinWaitlist(request);
        return ResponseEntity.ok(waitlist);
    }
}
