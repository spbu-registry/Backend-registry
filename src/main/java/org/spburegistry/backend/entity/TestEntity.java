package org.spburegistry.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestEntity {
    private int id;
    private String text;
}
