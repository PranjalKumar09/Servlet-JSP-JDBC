package com.pranjal.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private List<ProductDto> productDtoList;
    private long totalElements;
    private int totalPages;
    private Boolean isFirst;
    private Boolean isLast;
    private int pageNo;
    private int pageSize;
}
