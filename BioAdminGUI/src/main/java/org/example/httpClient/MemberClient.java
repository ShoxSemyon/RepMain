package org.example.httpClient;

import org.example.DTO.MemberDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange(url = "http://localhost:9090",
        contentType = MediaType.APPLICATION_JSON_VALUE,
        accept = MediaType.APPLICATION_JSON_VALUE)
public interface MemberClient {

    @GetExchange("/member")
    ResponseEntity<List<MemberDto>> getMembers();
}
