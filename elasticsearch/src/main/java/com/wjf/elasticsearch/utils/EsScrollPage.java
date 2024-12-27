package com.wjf.elasticsearch.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EsScrollPage<T> {

    private long total;

    private List<T> list;

    private String scrollId;
}