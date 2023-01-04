package com.gt.admin.catalogo.application.castmember.create;

import com.gt.admin.catalogo.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
                extends
                UseCase<CreateCastMemberCommand, CreateCastMemberOutput> permits DefaultCreateCastMemberUseCase {
}
