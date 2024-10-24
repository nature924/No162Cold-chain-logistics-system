package com.entity.vo;

import com.entity.HuowuFenpeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 订单分配
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("huowu_fenpei")
public class HuowuFenpeiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单
     */

    @TableField(value = "huowu_order_id")
    private Integer huowuOrderId;


    /**
     * 快递员
     */

    @TableField(value = "kuaidiyuan_id")
    private Integer kuaidiyuanId;


    /**
     * 订单类型
     */

    @TableField(value = "huowu_order_fenpei_types")
    private Integer huowuOrderFenpeiTypes;


    /**
     * 物流温度
     */

    @TableField(value = "fenpei_wendu")
    private Integer fenpeiWendu;


    /**
     * 分配时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "fenpei_time")
    private Date fenpeiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单
	 */
    public Integer getHuowuOrderId() {
        return huowuOrderId;
    }


    /**
	 * 获取：订单
	 */

    public void setHuowuOrderId(Integer huowuOrderId) {
        this.huowuOrderId = huowuOrderId;
    }
    /**
	 * 设置：快递员
	 */
    public Integer getKuaidiyuanId() {
        return kuaidiyuanId;
    }


    /**
	 * 获取：快递员
	 */

    public void setKuaidiyuanId(Integer kuaidiyuanId) {
        this.kuaidiyuanId = kuaidiyuanId;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getHuowuOrderFenpeiTypes() {
        return huowuOrderFenpeiTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setHuowuOrderFenpeiTypes(Integer huowuOrderFenpeiTypes) {
        this.huowuOrderFenpeiTypes = huowuOrderFenpeiTypes;
    }
    /**
	 * 设置：物流温度
	 */
    public Integer getFenpeiWendu() {
        return fenpeiWendu;
    }


    /**
	 * 获取：物流温度
	 */

    public void setFenpeiWendu(Integer fenpeiWendu) {
        this.fenpeiWendu = fenpeiWendu;
    }
    /**
	 * 设置：分配时间
	 */
    public Date getFenpeiTime() {
        return fenpeiTime;
    }


    /**
	 * 获取：分配时间
	 */

    public void setFenpeiTime(Date fenpeiTime) {
        this.fenpeiTime = fenpeiTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
