package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HuowuFenpeiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 订单分配
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("huowu_fenpei")
public class HuowuFenpeiView extends HuowuFenpeiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String huowuOrderFenpeiValue;

	//级联表 货物订单
		/**
		* 订单编号
		*/

		@ColumnInfo(comment="订单编号",type="varchar(200)")
		private String huowuOrderUuidNumber;
															 
		/**
		* 货物订单 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer huowuOrderYonghuId;
		/**
		* 订单类型
		*/
		@ColumnInfo(comment="订单类型",type="int(11)")
		private Integer huowuOrderTypes;
			/**
			* 订单类型的值
			*/
			@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
			private String huowuOrderValue;
	//级联表 快递员
							/**
		* 快递员姓名
		*/

		@ColumnInfo(comment="快递员姓名",type="varchar(200)")
		private String kuaidiyuanName;
		/**
		* 快递员手机号
		*/

		@ColumnInfo(comment="快递员手机号",type="varchar(200)")
		private String kuaidiyuanPhone;
		/**
		* 快递员身份证号
		*/

		@ColumnInfo(comment="快递员身份证号",type="varchar(200)")
		private String kuaidiyuanIdNumber;
		/**
		* 快递员头像
		*/

		@ColumnInfo(comment="快递员头像",type="varchar(200)")
		private String kuaidiyuanPhoto;
		/**
		* 快递员邮箱
		*/

		@ColumnInfo(comment="快递员邮箱",type="varchar(200)")
		private String kuaidiyuanEmail;



	public HuowuFenpeiView() {

	}

	public HuowuFenpeiView(HuowuFenpeiEntity huowuFenpeiEntity) {
		try {
			BeanUtils.copyProperties(this, huowuFenpeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getHuowuOrderFenpeiValue() {
		return huowuOrderFenpeiValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setHuowuOrderFenpeiValue(String huowuOrderFenpeiValue) {
		this.huowuOrderFenpeiValue = huowuOrderFenpeiValue;
	}


	//级联表的get和set 货物订单

		/**
		* 获取： 订单编号
		*/
		public String getHuowuOrderUuidNumber() {
			return huowuOrderUuidNumber;
		}
		/**
		* 设置： 订单编号
		*/
		public void setHuowuOrderUuidNumber(String huowuOrderUuidNumber) {
			this.huowuOrderUuidNumber = huowuOrderUuidNumber;
		}
		/**
		* 获取：货物订单 的 用户
		*/
		public Integer getHuowuOrderYonghuId() {
			return huowuOrderYonghuId;
		}
		/**
		* 设置：货物订单 的 用户
		*/
		public void setHuowuOrderYonghuId(Integer huowuOrderYonghuId) {
			this.huowuOrderYonghuId = huowuOrderYonghuId;
		}
		/**
		* 获取： 订单类型
		*/
		public Integer getHuowuOrderTypes() {
			return huowuOrderTypes;
		}
		/**
		* 设置： 订单类型
		*/
		public void setHuowuOrderTypes(Integer huowuOrderTypes) {
			this.huowuOrderTypes = huowuOrderTypes;
		}


			/**
			* 获取： 订单类型的值
			*/
			public String getHuowuOrderValue() {
				return huowuOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setHuowuOrderValue(String huowuOrderValue) {
				this.huowuOrderValue = huowuOrderValue;
			}
	//级联表的get和set 快递员

		/**
		* 获取： 快递员姓名
		*/
		public String getKuaidiyuanName() {
			return kuaidiyuanName;
		}
		/**
		* 设置： 快递员姓名
		*/
		public void setKuaidiyuanName(String kuaidiyuanName) {
			this.kuaidiyuanName = kuaidiyuanName;
		}

		/**
		* 获取： 快递员手机号
		*/
		public String getKuaidiyuanPhone() {
			return kuaidiyuanPhone;
		}
		/**
		* 设置： 快递员手机号
		*/
		public void setKuaidiyuanPhone(String kuaidiyuanPhone) {
			this.kuaidiyuanPhone = kuaidiyuanPhone;
		}

		/**
		* 获取： 快递员身份证号
		*/
		public String getKuaidiyuanIdNumber() {
			return kuaidiyuanIdNumber;
		}
		/**
		* 设置： 快递员身份证号
		*/
		public void setKuaidiyuanIdNumber(String kuaidiyuanIdNumber) {
			this.kuaidiyuanIdNumber = kuaidiyuanIdNumber;
		}

		/**
		* 获取： 快递员头像
		*/
		public String getKuaidiyuanPhoto() {
			return kuaidiyuanPhoto;
		}
		/**
		* 设置： 快递员头像
		*/
		public void setKuaidiyuanPhoto(String kuaidiyuanPhoto) {
			this.kuaidiyuanPhoto = kuaidiyuanPhoto;
		}

		/**
		* 获取： 快递员邮箱
		*/
		public String getKuaidiyuanEmail() {
			return kuaidiyuanEmail;
		}
		/**
		* 设置： 快递员邮箱
		*/
		public void setKuaidiyuanEmail(String kuaidiyuanEmail) {
			this.kuaidiyuanEmail = kuaidiyuanEmail;
		}


	@Override
	public String toString() {
		return "HuowuFenpeiView{" +
			", huowuOrderFenpeiValue=" + huowuOrderFenpeiValue +
			", huowuOrderUuidNumber=" + huowuOrderUuidNumber +
			", kuaidiyuanName=" + kuaidiyuanName +
			", kuaidiyuanPhone=" + kuaidiyuanPhone +
			", kuaidiyuanIdNumber=" + kuaidiyuanIdNumber +
			", kuaidiyuanPhoto=" + kuaidiyuanPhoto +
			", kuaidiyuanEmail=" + kuaidiyuanEmail +
			"} " + super.toString();
	}
}
