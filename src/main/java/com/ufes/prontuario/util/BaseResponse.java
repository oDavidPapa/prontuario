package com.ufes.prontuario.util;

import com.ufes.prontuario.dto.BasePageDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BaseResponse<T> {

    private boolean success = true;
    private Object data;
    private List<String> messages = new ArrayList<>();

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(List<T> data, long total) {
        this.data = new BasePageDTO<>(data, total);
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
