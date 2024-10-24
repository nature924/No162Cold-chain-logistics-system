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
 * 快递员
 *
 * @author 
 * @email
 */
@TableName("kuaidiyuan")
public class KuaidiyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KuaidiyuanEntity() {

	}

	public KuaidiyuanEntity(T t) {
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
     * 网点
     */
    @ColumnInfo(comment="网点",type="int(11)")
    @TableField(value = "wangdian_id")

    private Integer wangdianId;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 快递员姓名
     */
    @ColumnInfo(comment="快递员姓名",type="varchar(200)")
    @TableField(value = "kuaidiyuan_name")

    private String kuaidiyuanName;


    /**
     * 快递员手机号
     */
    @ColumnInfo(comment="快递员手机号",type="varchar(200)")
    @TableField(value = "kuaidiyuan_phone")

    private String kuaidiyuanPhone;


    /**
     * 快递员身份证号
     */
    @ColumnInfo(comment="快递员身份证号",type="varchar(200)")
    @TableField(value = "kuaidiyuan_id_number")

    private String kuaidiyuanIdNumber;


    /**
     * 快递员头像
     */
    @ColumnInfo(comment="快递员头像",type="varchar(200)")
    @TableField(value = "kuaidiyuan_photo")

    private String kuaidiyuanPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 快递员邮箱
     */
    @ColumnInfo(comment="快递员邮箱",type="varchar(200)")
    @TableField(value = "kuaidiyuan_email")

    private String kuaidiyuanEmail;


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

    @Override
    public String toString() {
        return "Kuaidiyuan{" +
            ", id=" + id +
            ", wangdianId=" + wangdianId +
            ", username=" + username +
            ", password=" + password +
            ", kuaidiyuanName=" + kuaidiyuanName +
            ", kuaidiyuanPhone=" + kuaidiyuanPhone +
            ", kuaidiyuanIdNumber=" + kuaidiyuanIdNumber +
            ", kuaidiyuanPhoto=" + kuaidiyuanPhoto +
            ", sexTypes=" + sexTypes +
            ", kuaidiyuanEmail=" + kuaidiyuanEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
