package com.dao;

import com.entity.HuowuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HuowuOrderView;

/**
 * 货物订单 Dao 接口
 *
 * @author 
 */
public interface HuowuOrderDao extends BaseMapper<HuowuOrderEntity> {

   List<HuowuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
