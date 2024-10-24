package com.entity.vo;

import com.entity.KuaidiyuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 快递员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("kuaidiyuan")
public class KuaidiyuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 网点
     */

    @TableField(value = "wangdian_id")
    private Integer wangdianId;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 快递员姓名
     */

    @TableField(value = "kuaidiyuan_name")
    private String kuaidiyuanName;


    /**
     * 快递员手机号
     */

    @TableField(value = "kuaidiyuan_phone")
    private String kuaidiyuanPhone;


    /**
     * 快递员身份证号
     */

    @TableField(value = "kuaidiyuan_id_number")
    private String kuaidiyuanIdNumber;


    /**
     * 快递员头像
     */

    @TableField(value = "kuaidiyuan_photo")
    private String kuaidiyuanPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 快递员邮箱
     */

    @TableField(value = "kuaidiyuan_email")
    private String kuaidiyuanEmail;


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
	 * 设置：网点
	 */
    public Integer getWangdianId() {
        return wangdianId;
    }


    /**
	 * 获取：网点
	 */

    public void setWangdianId(Integer wangdianId) {
        this.wangdianId = wangdianId;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：快递员姓名
	 */
    public String getKuaidiyuanName() {
        return kuaidiyuanName;
    }


    /**
	 * 获取：快递员姓名
	 */

    public void setKuaidiyuanName(String kuaidiyuanName) {
        this.kuaidiyuanName = kuaidiyuanName;
    }
    /**
	 * 设置：快递员手机号
	 */
    public String getKuaidiyuanPhone() {
        return kuaidiyuanPhone;
    }


    /**
	 * 获取：快递员手机号
	 */

    public void setKuaidiyuanPhone(String kuaidiyuanPhone) {
        this.kuaidiyuanPhone = kuaidiyuanPhone;
    }
    /**
	 * 设置：快递员身份证号
	 */
    public String getKuaidiyuanIdNumber() {
        return kuaidiyuanIdNumber;
    }


    /**
	 * 获取：快递员身份证号
	 */

    public void setKuaidiyuanIdNumber(String kuaidiyuanIdNumber) {
        this.kuaidiyuanIdNumber = kuaidiyuanIdNumber;
    }
    /**
	 * 设置：快递员头像
	 */
    public String getKuaidiyuanPhoto() {
        return kuaidiyuanPhoto;
    }


    /**
	 * 获取：快递员头像
	 */

    public void setKuaidiyuanPhoto(String kuaidiyuanPhoto) {
        this.kuaidiyuanPhoto = kuaidiyuanPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：快递员邮箱
	 */
    public String getKuaidiyuanEmail() {
        return kuaidiyuanEmail;
    }


    /**
	 * 获取：快递员邮箱
	 */

    public void setKuaidiyuanEmail(String kuaidiyuanEmail) {
        this.kuaidiyuanEmail = kuaidiyuanEmail;
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
