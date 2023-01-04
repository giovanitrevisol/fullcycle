package com.gt.admin.catalogo.application.genre.retrieve.list;

import java.time.Instant;
import java.util.List;

import com.gt.admin.catalogo.domain.category.CategoryID;
import com.gt.admin.catalogo.domain.genre.Genre;

public record GenreListOutput(
                String id,
                String name,
                boolean isActive,
                List<String> categories,
                Instant createdAt,
                Instant deletedAt) {

        public static GenreListOutput from(final Genre aGenre) {
                return new GenreListOutput(
                                aGenre.getId().getValue(),
                                aGenre.getName(),
                                aGenre.isActive(),
                                aGenre.getCategories().stream()
                                                .map(CategoryID::getValue)
                                                .toList(),
                                aGenre.getCreatedAt(),
                                aGenre.getDeletedAt());
        }
}
