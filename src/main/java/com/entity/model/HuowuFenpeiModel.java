package com.entity.model;

import com.entity.HuowuFenpeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 订单分配
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HuowuFenpeiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单
     */
    private Integer huowuOrderId;


    /**
     * 快递员
     */
    private Integer kuaidiyuanId;


    /**
     * 订单类型
     */
    private Integer huowuOrderFenpeiTypes;


    /**
     * 物流温度
     */
    private Integer fenpeiWendu;


    /**
     * 分配时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date fenpeiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单
	 */
    public Integer getHuowuOrderId() {
        return huowuOrderId;
    }


    /**
	 * 设置：订单
	 */
    public void setHuowuOrderId(Integer huowuOrderId) {
        this.huowuOrderId = huowuOrderId;
    }
    /**
	 * 获取：快递员
	 */
    public Integer getKuaidiyuanId() {
        return kuaidiyuanId;
    }


    /**
	 * 设置：快递员
	 */
    public void setKuaidiyuanId(Integer kuaidiyuanId) {
        this.kuaidiyuanId = kuaidiyuanId;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getHuowuOrderFenpeiTypes() {
        return huowuOrderFenpeiTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setHuowuOrderFenpeiTypes(Integer huowuOrderFenpeiTypes) {
        this.huowuOrderFenpeiTypes = huowuOrderFenpeiTypes;
    }
    /**
	 * 获取：物流温度
	 */
    public Integer getFenpeiWendu() {
        return fenpeiWendu;
    }


    /**
	 * 设置：物流温度
	 */
    public void setFenpeiWendu(Integer fenpeiWendu) {
        this.fenpeiWendu = fenpeiWendu;
    }
    /**
	 * 获取：分配时间
	 */
    public Date getFenpeiTime() {
        return fenpeiTime;
    }


    /**
	 * 设置：分配时间
	 */
    public void setFenpeiTime(Date fenpeiTime) {
        this.fenpeiTime = fenpeiTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
