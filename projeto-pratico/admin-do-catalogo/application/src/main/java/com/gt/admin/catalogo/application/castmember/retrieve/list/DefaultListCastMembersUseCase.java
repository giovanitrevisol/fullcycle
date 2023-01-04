package com.gt.admin.catalogo.application.castmember.retrieve.list;

import java.util.Objects;

import com.gt.admin.catalogo.domain.castmember.CastMemberGateway;
import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;

public non-sealed class DefaultListCastMembersUseCase extends ListCastMembersUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultListCastMembersUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public Pagination<CastMemberListOutput> execute(final SearchQuery aQuery) {
        return this.castMemberGateway.findAll(aQuery)
                .map(CastMemberListOutput::from);
    }
}
