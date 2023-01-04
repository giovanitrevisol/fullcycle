package com.gt.admin.catalogo.application.castmember.retrieve.get;

import java.util.Objects;

import com.gt.admin.catalogo.domain.castmember.CastMember;
import com.gt.admin.catalogo.domain.castmember.CastMemberGateway;
import com.gt.admin.catalogo.domain.castmember.CastMemberID;
import com.gt.admin.catalogo.domain.exceptions.NotFoundException;

public non-sealed class DefaultGetCastMemberByIdUseCase extends GetCastMemberByIdUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultGetCastMemberByIdUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public CastMemberOutput execute(final String anIn) {
        final var aMemberId = CastMemberID.from(anIn);
        return this.castMemberGateway.findById(aMemberId)
                .map(CastMemberOutput::from)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberId));
    }
}
