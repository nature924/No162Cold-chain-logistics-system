package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 订单分配
 *
 * @author 
 * @email
 */
@TableName("huowu_fenpei")
public class HuowuFenpeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HuowuFenpeiEntity() {

	}

	public HuowuFenpeiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单
     */
    @ColumnInfo(comment="订单",type="int(11)")
    @TableField(value = "huowu_order_id")

    private Integer huowuOrderId;


    /**
     * 快递员
     */
    @ColumnInfo(comment="快递员",type="int(11)")
    @TableField(value = "kuaidiyuan_id")

    private Integer kuaidiyuanId;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "huowu_order_fenpei_types")

    private Integer huowuOrderFenpeiTypes;


    /**
     * 物流温度
     */
    @ColumnInfo(comment="物流温度",type="int(11)")
    @TableField(value = "fenpei_wendu")

    private Integer fenpeiWendu;


    /**
     * 分配时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="分配时间",type="timestamp")
    @TableField(value = "fenpei_time")

    private Date fenpeiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "HuowuFenpei{" +
            ", id=" + id +
            ", huowuOrderId=" + huowuOrderId +
            ", kuaidiyuanId=" + kuaidiyuanId +
            ", huowuOrderFenpeiTypes=" + huowuOrderFenpeiTypes +
            ", fenpeiWendu=" + fenpeiWendu +
            ", fenpeiTime=" + DateUtil.convertString(fenpeiTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
