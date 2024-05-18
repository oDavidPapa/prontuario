package com.ufes.prontuario.dto;

import lombok.Data;

import java.util.List;

@Data
public class BasePageDTO<T> {

    private List<T> list;
    private long total;

    public BasePageDTO(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }
}
