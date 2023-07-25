package com.hms.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PageableResponse {

    private long total;

    private long count;
    private List<?> data;

    public void setList(List<?> list) {
    this.data = list;
}

    public void setTotal(long total) {
    this.count = total;
    this.total = total;
    }
}
