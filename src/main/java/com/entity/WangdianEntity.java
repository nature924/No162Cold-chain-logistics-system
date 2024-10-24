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
 * 网点信息
 *
 * @author 
 * @email
 */
@TableName("wangdian")
public class WangdianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WangdianEntity() {

	}

	public WangdianEntity(T t) {
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
     * 网点名称
     */
    @ColumnInfo(comment="网点名称",type="varchar(200)")
    @TableField(value = "wangdian_name")

    private String wangdianName;


    /**
     * 网点照片
     */
    @ColumnInfo(comment="网点照片",type="varchar(200)")
    @TableField(value = "wangdain_photo")

    private String wangdainPhoto;


    /**
     * 联系人姓名
     */
    @ColumnInfo(comment="联系人姓名",type="varchar(200)")
    @TableField(value = "lianxiren_name")

    private String lianxirenName;


    /**
     * 联系人手机号
     */
    @ColumnInfo(comment="联系人手机号",type="varchar(200)")
    @TableField(value = "lianxiren_phone")

    private String lianxirenPhone;


    /**
     * 地址
     */
    @ColumnInfo(comment="地址",type="varchar(200)")
    @TableField(value = "lianxiren_address")

    private String lianxirenAddress;


    /**
     * 网点类型
     */
    @ColumnInfo(comment="网点类型",type="int(11)")
    @TableField(value = "wangdian_types")

    private Integer wangdianTypes;


    /**
     * 网点详情
     */
    @ColumnInfo(comment="网点详情",type="longtext")
    @TableField(value = "wangdian_content")

    private String wangdianContent;


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
	 * 获取：网点名称
	 */
    public String getWangdianName() {
        return wangdianName;
    }
    /**
	 * 设置：网点名称
	 */

    public void setWangdianName(String wangdianName) {
        this.wangdianName = wangdianName;
    }
    /**
	 * 获取：网点照片
	 */
    public String getWangdainPhoto() {
        return wangdainPhoto;
    }
    /**
	 * 设置：网点照片
	 */

    public void setWangdainPhoto(String wangdainPhoto) {
        this.wangdainPhoto = wangdainPhoto;
    }
    /**
	 * 获取：联系人姓名
	 */
    public String getLianxirenName() {
        return lianxirenName;
    }
    /**
	 * 设置：联系人姓名
	 */

    public void setLianxirenName(String lianxirenName) {
        this.lianxirenName = lianxirenName;
    }
    /**
	 * 获取：联系人手机号
	 */
    public String getLianxirenPhone() {
        return lianxirenPhone;
    }
    /**
	 * 设置：联系人手机号
	 */

    public void setLianxirenPhone(String lianxirenPhone) {
        this.lianxirenPhone = lianxirenPhone;
    }
    /**
	 * 获取：地址
	 */
    public String getLianxirenAddress() {
        return lianxirenAddress;
    }
    /**
	 * 设置：地址
	 */

    public void setLianxirenAddress(String lianxirenAddress) {
        this.lianxirenAddress = lianxirenAddress;
    }
    /**
	 * 获取：网点类型
	 */
    public Integer getWangdianTypes() {
        return wangdianTypes;
    }
    /**
	 * 设置：网点类型
	 */

    public void setWangdianTypes(Integer wangdianTypes) {
        this.wangdianTypes = wangdianTypes;
    }
    /**
	 * 获取：网点详情
	 */
    public String getWangdianContent() {
        return wangdianContent;
    }
    /**
	 * 设置：网点详情
	 */

    public void setWangdianContent(String wangdianContent) {
        this.wangdianContent = wangdianContent;
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
        return "Wangdian{" +
            ", id=" + id +
            ", wangdianName=" + wangdianName +
            ", wangdainPhoto=" + wangdainPhoto +
            ", lianxirenName=" + lianxirenName +
            ", lianxirenPhone=" + lianxirenPhone +
            ", lianxirenAddress=" + lianxirenAddress +
            ", wangdianTypes=" + wangdianTypes +
            ", wangdianContent=" + wangdianContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
