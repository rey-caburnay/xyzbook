package com.montani.exam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaDTO {

    String query;
    int pageSize;
    int totalPages;
    int currentPage;
}
