package com.gt.admin.catalogo.infrastructure.utils;

public class SqlUtils {

    private SqlUtils() {
    }

    public static String upper(final String term) {
        if (term == null) return null;
        return term.toUpperCase();
    }

    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}
