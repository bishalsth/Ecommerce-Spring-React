package com.bishal.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddItemRequest {

    private Long productId;
    private String size;
    private int quantity;
    private Integer price;

}
