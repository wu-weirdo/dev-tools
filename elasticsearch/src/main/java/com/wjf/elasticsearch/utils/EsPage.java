package com.wjf.elasticsearch.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EsPage<T> {

    private int startPage;

    private int pageSize;

    private long total;

    private List<T> list;
}