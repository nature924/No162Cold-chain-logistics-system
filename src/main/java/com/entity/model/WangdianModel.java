package com.entity.model;

import com.entity.WangdianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 网点信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WangdianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 网点名称
     */
    private String wangdianName;


    /**
     * 联系人姓名
     */
    private String lianxirenName;


    /**
     * 联系人手机号
     */
    private String lianxirenPhone;


    /**
     * 地址
     */
    private String lianxirenAddress;


    /**
     * 网点类型
     */
    private Integer wangdianTypes;


    /**
     * 网点详情
     */
    private String wangdianContent;


    /**
     * 创建时间 show2 photoShow
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
	 * 获取：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
