package com.zekademi.strongprettyhomes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageDTO {

    private String name;
    private String url;
    private String type;
    private long size;
}
