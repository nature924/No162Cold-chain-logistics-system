package com.dao;

import com.entity.WangdianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WangdianView;

/**
 * 网点信息 Dao 接口
 *
 * @author 
 */
public interface WangdianDao extends BaseMapper<WangdianEntity> {

   List<WangdianView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
