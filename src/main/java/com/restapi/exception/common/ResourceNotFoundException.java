package com.restapi.exception.common;

import lombok.Getter;

/**
 * Custom exception class for representing situations where a requested resource is not found.
 * This exception is typically thrown when an entity, such as a post or user, is not present in the system.
 */
@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    /**
     * Constructs an instance of ResourceNotFoundException with details about the missing resource.
     *
     * @param resourceName The name of the resource that was not found (e.g., "Post", "User").
     * @param fieldName    The name of the field used for lookup (e.g., "id", "username").
     * @param fieldValue   The value of the field that was used for lookup (e.g., the specific ID).
     */
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)); // Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
