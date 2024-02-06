package org.example.converter;

import jakarta.persistence.AttributeConverter;
import org.example.model.ArticleStatus;

public class ArticleStatusConverter implements AttributeConverter<ArticleStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleStatus status) {
        return switch (status) {
            case PUBLISH -> 0;
            case DRAFT -> 1;
            case TRASH -> 2;
            case PRIVATE -> 3;
        };
    }

    @Override
    public ArticleStatus convertToEntityAttribute(Integer statusValue) {
        return switch (statusValue) {
            case 0 -> ArticleStatus.PUBLISH;
            case 1 -> ArticleStatus.DRAFT;
            case 2 -> ArticleStatus.TRASH;
            case 3 -> ArticleStatus.PRIVATE;
            default -> throw new IllegalStateException("Unexpected value" + statusValue);
        };
    }
}

