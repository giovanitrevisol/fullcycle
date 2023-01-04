package com.gt.admin.catalogo.application.category.update;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.validation.handler.Notification;

import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
                extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
