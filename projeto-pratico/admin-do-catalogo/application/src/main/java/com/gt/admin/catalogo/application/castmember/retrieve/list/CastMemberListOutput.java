package com.gt.admin.catalogo.application.castmember.retrieve.list;

import java.time.Instant;

import com.gt.admin.catalogo.domain.castmember.CastMember;
import com.gt.admin.catalogo.domain.castmember.CastMemberType;

public record CastMemberListOutput(
        String id,
        String name,
        CastMemberType type,
        Instant createdAt) {

    public static CastMemberListOutput from(final CastMember aMember) {
        return new CastMemberListOutput(
                aMember.getId().getValue(),
                aMember.getName(),
                aMember.getType(),
                aMember.getCreatedAt());
    }
}
