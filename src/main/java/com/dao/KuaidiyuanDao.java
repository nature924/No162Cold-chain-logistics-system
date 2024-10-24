package com.dao;

import com.entity.KuaidiyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.KuaidiyuanView;

/**
 * 快递员 Dao 接口
 *
 * @author 
 */
public interface KuaidiyuanDao extends BaseMapper<KuaidiyuanEntity> {

   List<KuaidiyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
