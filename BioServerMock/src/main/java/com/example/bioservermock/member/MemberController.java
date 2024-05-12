package com.example.bioservermock.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping
    public ResponseEntity<List<MemberDto>> get() {
        return new ResponseEntity<>(List.of(new MemberDto(1,
                1,
                "Test",
                "Test",
                "Test",
                Instant.now(),
                Instant.now(),
                Instant.now(),
                null,
                List.of("KBS"))
        ), HttpStatus.OK);
    }
}
