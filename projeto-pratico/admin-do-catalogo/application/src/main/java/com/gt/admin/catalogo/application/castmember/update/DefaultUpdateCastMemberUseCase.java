package com.gt.admin.catalogo.application.castmember.update;

import java.util.Objects;
import java.util.function.Supplier;

import com.gt.admin.catalogo.domain.Identifier;
import com.gt.admin.catalogo.domain.castmember.CastMember;
import com.gt.admin.catalogo.domain.castmember.CastMemberGateway;
import com.gt.admin.catalogo.domain.castmember.CastMemberID;
import com.gt.admin.catalogo.domain.exceptions.NotFoundException;
import com.gt.admin.catalogo.domain.exceptions.NotificationException;
import com.gt.admin.catalogo.domain.validation.handler.Notification;

public non-sealed class DefaultUpdateCastMemberUseCase extends UpdateCastMemberUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultUpdateCastMemberUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public UpdateCastMemberOutput execute(final UpdateCastMemberCommand aCommand) {
        final var anId = CastMemberID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aType = aCommand.type();

        final var aMember = this.castMemberGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.validate(() -> aMember.update(aName, aType));

        if (notification.hasError()) {
            notify(anId, notification);
        }

        return UpdateCastMemberOutput.from(this.castMemberGateway.update(aMember));
    }

    private void notify(final Identifier anId, final Notification notification) {
        throw new NotificationException("Could not update Aggregate CastMember %s".formatted(anId.getValue()),
                notification);
    }

    private Supplier<NotFoundException> notFound(final CastMemberID anId) {
        return () -> NotFoundException.with(CastMember.class, anId);
    }
}
