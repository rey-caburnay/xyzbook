package com.montani.exam.converter;

import com.montani.exam.dto.PublisherDTO;
import com.montani.exam.model.Publisher;

public class PublisherConverter {

    public static PublisherDTO toDTO(Publisher entity) {
        PublisherDTO dto = new PublisherDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
