package com.workintech.fswebs18challengemaven.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private String color;
    private String type;
    private Integer value;
}
