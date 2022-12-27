package com.gt.admin.catalogo.domain.category;

import com.gt.admin.catalogo.domain.AggregateRoot;
import com.gt.admin.catalogo.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryID> {

    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final boolean anActive,
            final Instant aCreationDate,
            final Instant aUpdateDate,
            final Instant aDeleteDate
    )
    {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = anActive;
        this.createdAt = aCreationDate;
        this.updatedAt = aUpdateDate;
        this.deletedAt = aDeleteDate;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean isActive){
       final var id = CategoryID.unique();
       final var now = Instant.now();
       final var deleteAt = isActive ? null : now;

        return new Category(id, aName, aDescription, isActive, now, now,null);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }
    public Category deactivate(){
        if(getDeletedAt() == null){
            this.deletedAt = Instant.now();
        }
        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category update(final String aName, final String aDescription,
                           final boolean isActive){
        this.name = aName;
        this.description = aDescription;
        if (isActive){
            activate();
        } else {
            deactivate();
        }
        this.updatedAt = Instant.now();
        return this;
    }

    public CategoryID getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
