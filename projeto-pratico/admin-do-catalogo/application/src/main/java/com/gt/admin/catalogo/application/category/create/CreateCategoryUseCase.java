package com.gt.admin.catalogo.application.category.create;

import com.gt.admin.catalogo.application.UseCase;
import com.gt.admin.catalogo.domain.validation.handler.Notification;

import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
                extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
