package com.example.bioservermock.member;

import java.time.Instant;
import java.util.List;

public record MemberDto(
        Integer id,
        Integer validationId,
        String name,
        String notificationUrl,
        String sbpCallbackUrl,
        Instant createdAt,
        Instant modifiedAt,
        Instant deactivatedAt,
        Integer parentMemberId,
        List<String> roles
) {

}
