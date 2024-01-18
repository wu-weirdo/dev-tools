package com.whf.statemachine.mapper;

import com.whf.statemachine.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author hfwu
* @description 针对表【order(订单表)】的数据库操作Mapper
* @createDate 2024-01-18 14:24:32
* @Entity com.whf.statemachine.entity.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




