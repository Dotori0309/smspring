package edu.sm.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ProductSearch {
    String productName;
    String startDate;
    String endDate;
    Integer cateId = 0;
    Integer startPrice = 0;
    Integer endPrice = 0;
}
