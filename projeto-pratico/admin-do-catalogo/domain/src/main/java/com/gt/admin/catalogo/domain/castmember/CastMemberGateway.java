package com.gt.admin.catalogo.domain.castmember;

import java.util.List;
import java.util.Optional;

import com.gt.admin.catalogo.domain.pagination.Pagination;
import com.gt.admin.catalogo.domain.pagination.SearchQuery;

public interface CastMemberGateway {

    CastMember create(CastMember aCastMember);

    void deleteById(CastMemberID anId);

    Optional<CastMember> findById(CastMemberID anId);

    CastMember update(CastMember aCastMember);

    Pagination<CastMember> findAll(SearchQuery aQuery);

    List<CastMemberID> existsByIds(Iterable<CastMemberID> ids);
}
