package com.entity.vo;

import com.entity.WangdianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 网点信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("wangdian")
public class WangdianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 网点名称
     */

    @TableField(value = "wangdian_name")
    private String wangdianName;


    /**
     * 联系人姓名
     */

    @TableField(value = "lianxiren_name")
    private String lianxirenName;


    /**
     * 联系人手机号
     */

    @TableField(value = "lianxiren_phone")
    private String lianxirenPhone;


    /**
     * 地址
     */

    @TableField(value = "lianxiren_address")
    private String lianxirenAddress;


    /**
     * 网点类型
     */

    @TableField(value = "wangdian_types")
    private Integer wangdianTypes;


    /**
     * 网点详情
     */

    @TableField(value = "wangdian_content")
    private String wangdianContent;


    /**
     * 创建时间 show2 photoShow
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
	 * 设置：网点名称
	 */
    public String getWangdianName() {
        return wangdianName;
    }


    /**
	 * 获取：网点名称
	 */

    public void setWangdianName(String wangdianName) {
        this.wangdianName = wangdianName;
    }
    /**
	 * 设置：联系人姓名
	 */
    public String getLianxirenName() {
        return lianxirenName;
    }


    /**
	 * 获取：联系人姓名
	 */

    public void setLianxirenName(String lianxirenName) {
        this.lianxirenName = lianxirenName;
    }
    /**
	 * 设置：联系人手机号
	 */
    public String getLianxirenPhone() {
        return lianxirenPhone;
    }


    /**
	 * 获取：联系人手机号
	 */

    public void setLianxirenPhone(String lianxirenPhone) {
        this.lianxirenPhone = lianxirenPhone;
    }
    /**
	 * 设置：地址
	 */
    public String getLianxirenAddress() {
        return lianxirenAddress;
    }


    /**
	 * 获取：地址
	 */

    public void setLianxirenAddress(String lianxirenAddress) {
        this.lianxirenAddress = lianxirenAddress;
    }
    /**
	 * 设置：网点类型
	 */
    public Integer getWangdianTypes() {
        return wangdianTypes;
    }


    /**
	 * 获取：网点类型
	 */

    public void setWangdianTypes(Integer wangdianTypes) {
        this.wangdianTypes = wangdianTypes;
    }
    /**
	 * 设置：网点详情
	 */
    public String getWangdianContent() {
        return wangdianContent;
    }


    /**
	 * 获取：网点详情
	 */

    public void setWangdianContent(String wangdianContent) {
        this.wangdianContent = wangdianContent;
    }
    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
