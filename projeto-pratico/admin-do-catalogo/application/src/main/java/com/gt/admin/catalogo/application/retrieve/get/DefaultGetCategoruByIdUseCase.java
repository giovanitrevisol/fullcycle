package com.gt.admin.catalogo.application.retrieve.get;

import com.gt.admin.catalogo.domain.category.CategoryGateway;
import com.gt.admin.catalogo.domain.category.CategoryID;
import com.gt.admin.catalogo.domain.exceptions.DomainException;
import com.gt.admin.catalogo.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoruByIdUseCase extends GetCategoruByIdUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoruByIdUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIN) {
        final var aCategoryID = CategoryID.from(anIN);
        return this.categoryGateway.findById(aCategoryID)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(aCategoryID));
    }

    private static Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> DomainException
                .with(new Error("Category with ID %s was not found".formatted(anId.getValue()))
                );
    }
}
