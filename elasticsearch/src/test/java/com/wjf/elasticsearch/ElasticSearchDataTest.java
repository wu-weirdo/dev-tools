//package com.wjf.elasticsearch;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.sinochem.center.entity.TaskCenter;
//import com.wjf.elasticsearch.utils.ElasticsearchUtil;
//import com.wjf.elasticsearch.utils.EsPage;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class ElasticSearchDataTest {
//
//    @Autowired
//    private ElasticsearchUtil elasticsearchUtil;
//
//
//    /**
//     * 创建索引
//     * @throws IOException
//     */
//    @Test
//    public void addIndex() throws IOException {
//        boolean b = elasticsearchUtil.createIndex("hxy_task");
//        System.out.println(b);
//    }
//
//    /**
//     * 新增/修改文档信息
//     * @throws IOException
//     */
//    @Test
//    public void insertDocument() throws IOException {
//        TaskCenter taskCenter = taskCenterMapper.selectById(100874);
//        String id = elasticsearchUtil.insertDocument("hxy_task", taskCenter);
//        System.out.println(id);
//    }
//
//    /**
//     * 根据id查询文档
//     * @throws IOException
//     */
//    @Test
//    public void selectDocumentById() throws IOException {
//        TaskCenter taskCenter = elasticsearchUtil.selectDocumentById("hxy_task", "4B0895F2B2254406B1A83E689E624D41", TaskCenter.class);
//        System.out.println(taskCenter);
//    }
//
//    /**
//     * 批量操作(新增)
//     * @throws IOException
//     */
//    @Test
//    public void patch() throws IOException {
//        QueryWrapper<TaskCenter> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_code","hhj");
//        List<TaskCenter> taskCenterList = taskCenterMapper.selectList(queryWrapper);
//        List<Object> objects = new ArrayList<>();
//        objects.addAll(taskCenterList);
//        boolean b = elasticsearchUtil.patch("hxy_task","insert",objects,5);
//        System.out.println(b);
//    }
//
//    /**
//     * （筛选条件）获取数据集合
//     * @throws IOException
//     */
//    @Test
//    public void selectDocumentList() throws IOException {
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.sort("createTime", SortOrder.DESC);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<font color=\"red\">");
//        highlightBuilder.postTags("</font>");
//        highlightBuilder.highlighterType("unified");
//        highlightBuilder.field("taskItemTitle");
//        highlightBuilder.field("orgName");
//        highlightBuilder.requireFieldMatch(false);//多次段高亮需要设置为false
//        searchSourceBuilder.highlighter(highlightBuilder);
//
//        QueryBuilder qb = QueryBuilders.matchQuery("taskItemTitle","中化塑料有限公司");
//        searchSourceBuilder.query(qb);
//
//        List<TaskCenter> taskCenterList = elasticsearchUtil.selectDocumentList("hxy_task", searchSourceBuilder, TaskCenter.class);
//        System.out.println(taskCenterList);
//    }
//
//    /**
//     * （筛选条件）获取数据集合分页
//     * @throws IOException
//     */
//    @Test
//    public void selectDocumentPage() throws IOException {
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.sort("createTime", SortOrder.DESC);
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.preTags("<font color=\"red\">");
//        highlightBuilder.postTags("</font>");
//        highlightBuilder.highlighterType("unified");
//        highlightBuilder.field("taskItemTitle");
//        highlightBuilder.field("orgName");
//        highlightBuilder.requireFieldMatch(false);//多次段高亮需要设置为false
//        searchSourceBuilder.highlighter(highlightBuilder);
//
//        QueryBuilder qb = QueryBuilders.matchQuery("taskItemTitle","中化塑料有限公司");
//        searchSourceBuilder.query(qb);
//
//        EsPage page = elasticsearchUtil.selectDocumentPage("hxy_task", searchSourceBuilder, 1, 5, TaskCenter.class);
//        System.out.println(page);
//    }
//}
//
