package com.entity.model;

import com.entity.KuaidiyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 快递员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class KuaidiyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 网点
     */
    private Integer wangdianId;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 快递员姓名
     */
    private String kuaidiyuanName;


    /**
     * 快递员手机号
     */
    private String kuaidiyuanPhone;


    /**
     * 快递员身份证号
     */
    private String kuaidiyuanIdNumber;


    /**
     * 快递员头像
     */
    private String kuaidiyuanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 快递员邮箱
     */
    private String kuaidiyuanEmail;


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
	 * 获取：网点
	 */
    public Integer getWangdianId() {
        return wangdianId;
    }


    /**
	 * 设置：网点
	 */
    public void setWangdianId(Integer wangdianId) {
        this.wangdianId = wangdianId;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：快递员姓名
	 */
    public String getKuaidiyuanName() {
        return kuaidiyuanName;
    }


    /**
	 * 设置：快递员姓名
	 */
    public void setKuaidiyuanName(String kuaidiyuanName) {
        this.kuaidiyuanName = kuaidiyuanName;
    }
    /**
	 * 获取：快递员手机号
	 */
    public String getKuaidiyuanPhone() {
        return kuaidiyuanPhone;
    }


    /**
	 * 设置：快递员手机号
	 */
    public void setKuaidiyuanPhone(String kuaidiyuanPhone) {
        this.kuaidiyuanPhone = kuaidiyuanPhone;
    }
    /**
	 * 获取：快递员身份证号
	 */
    public String getKuaidiyuanIdNumber() {
        return kuaidiyuanIdNumber;
    }


    /**
	 * 设置：快递员身份证号
	 */
    public void setKuaidiyuanIdNumber(String kuaidiyuanIdNumber) {
        this.kuaidiyuanIdNumber = kuaidiyuanIdNumber;
    }
    /**
	 * 获取：快递员头像
	 */
    public String getKuaidiyuanPhoto() {
        return kuaidiyuanPhoto;
    }


    /**
	 * 设置：快递员头像
	 */
    public void setKuaidiyuanPhoto(String kuaidiyuanPhoto) {
        this.kuaidiyuanPhoto = kuaidiyuanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：快递员邮箱
	 */
    public String getKuaidiyuanEmail() {
        return kuaidiyuanEmail;
    }


    /**
	 * 设置：快递员邮箱
	 */
    public void setKuaidiyuanEmail(String kuaidiyuanEmail) {
        this.kuaidiyuanEmail = kuaidiyuanEmail;
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
