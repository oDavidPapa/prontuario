package com.ufes.prontuario.util;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class PageUtils {

    public static Pageable preparePageable(@Nullable Pageable pageable) {
        if(Objects.isNull(pageable)) {
            return Pageable.unpaged();
        }

        return pageable;
    }
}
