package com.gt.admin.catalogo.domain.genre;

import java.util.Objects;

import com.gt.admin.catalogo.domain.Identifier;
import com.gt.admin.catalogo.domain.utils.IdUtils;

public class GenreID extends Identifier {

    private final String value;

    private GenreID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static GenreID unique() {
        return GenreID.from(IdUtils.uuid());
    }

    public static GenreID from(final String anId) {
        return new GenreID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final GenreID that = (GenreID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
