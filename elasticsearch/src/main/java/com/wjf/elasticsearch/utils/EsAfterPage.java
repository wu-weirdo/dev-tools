package com.wjf.elasticsearch.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EsAfterPage<T> {

    private long total;

    private List<T> list;

    private String afterId;
}