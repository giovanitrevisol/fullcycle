package com.gt.admin.catalogo.infrastructure.castmember.models;

import com.gt.admin.catalogo.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
