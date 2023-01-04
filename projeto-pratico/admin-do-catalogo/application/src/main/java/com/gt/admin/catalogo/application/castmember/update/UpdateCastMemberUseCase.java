package com.gt.admin.catalogo.application.castmember.update;

import com.gt.admin.catalogo.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
                extends
                UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput> permits DefaultUpdateCastMemberUseCase {
}
