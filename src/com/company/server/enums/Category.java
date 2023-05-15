package com.company.server.enums;

import java.util.UUID;

public enum Category {
    TEXNIKA(UUID.randomUUID()),
    ODEJDA(UUID.randomUUID()),
    KOSMETIKA(UUID.randomUUID());
    final UUID ID;
    Category(UUID id) {
        ID = id;
    }
    public UUID category_id(){
        return ID;
    }
}
