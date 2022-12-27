package com.gt.admin.catalogo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UseCaseTest {

    @Test
    public void testCreateUseCase(){
        Assertions.assertNotNull(new UseCase());
        Assertions.assertNotNull(new UseCase().execute());
    }

}