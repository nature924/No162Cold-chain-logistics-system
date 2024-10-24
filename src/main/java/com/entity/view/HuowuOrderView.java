package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HuowuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 货物订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("huowu_order")
public class HuowuOrderView extends HuowuOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String huowuOrderValue;

	//级联表 收货地址
					 
		/**
		* 收货地址 的 创建用户
		*/
		@ColumnInfo(comment="创建用户",type="int(20)")
		private Integer addressYonghuId;
		/**
		* 收货人
		*/

		@ColumnInfo(comment="收货人",type="varchar(200)")
		private String addressName;
		/**
		* 电话
		*/

		@ColumnInfo(comment="电话",type="varchar(200)")
		private String addressPhone;
		/**
		* 地址
		*/

		@ColumnInfo(comment="地址",type="varchar(200)")
		private String addressDizhi;
		/**
		* 是否默认地址
		*/
		@ColumnInfo(comment="是否默认地址",type="int(11)")
		private Integer isdefaultTypes;
			/**
			* 是否默认地址的值
			*/
			@ColumnInfo(comment="是否默认地址的字典表值",type="varchar(200)")
			private String isdefaultValue;
	//级联表 货物
		/**
		* 货物名称
		*/

		@ColumnInfo(comment="货物名称",type="varchar(200)")
		private String huowuName;
		/**
		* 货物编号
		*/

		@ColumnInfo(comment="货物编号",type="varchar(200)")
		private String huowuUuidNumber;
		/**
		* 货物照片
		*/

		@ColumnInfo(comment="货物照片",type="varchar(200)")
		private String huowuPhoto;
		/**
		* 货物类型
		*/
		@ColumnInfo(comment="货物类型",type="int(11)")
		private Integer huowuTypes;
			/**
			* 货物类型的值
			*/
			@ColumnInfo(comment="货物类型的字典表值",type="varchar(200)")
			private String huowuValue;
		/**
		* 货物介绍
		*/

		@ColumnInfo(comment="货物介绍",type="longtext")
		private String huowuContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer huowuDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;



	public HuowuOrderView() {

	}

	public HuowuOrderView(HuowuOrderEntity huowuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, huowuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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


	//级联表的get和set 收货地址
		/**
		* 获取：收货地址 的 创建用户
		*/
		public Integer getAddressYonghuId() {
			return addressYonghuId;
		}
		/**
		* 设置：收货地址 的 创建用户
		*/
		public void setAddressYonghuId(Integer addressYonghuId) {
			this.addressYonghuId = addressYonghuId;
		}

		/**
		* 获取： 收货人
		*/
		public String getAddressName() {
			return addressName;
		}
		/**
		* 设置： 收货人
		*/
		public void setAddressName(String addressName) {
			this.addressName = addressName;
		}

		/**
		* 获取： 电话
		*/
		public String getAddressPhone() {
			return addressPhone;
		}
		/**
		* 设置： 电话
		*/
		public void setAddressPhone(String addressPhone) {
			this.addressPhone = addressPhone;
		}

		/**
		* 获取： 地址
		*/
		public String getAddressDizhi() {
			return addressDizhi;
		}
		/**
		* 设置： 地址
		*/
		public void setAddressDizhi(String addressDizhi) {
			this.addressDizhi = addressDizhi;
		}
		/**
		* 获取： 是否默认地址
		*/
		public Integer getIsdefaultTypes() {
			return isdefaultTypes;
		}
		/**
		* 设置： 是否默认地址
		*/
		public void setIsdefaultTypes(Integer isdefaultTypes) {
			this.isdefaultTypes = isdefaultTypes;
		}


			/**
			* 获取： 是否默认地址的值
			*/
			public String getIsdefaultValue() {
				return isdefaultValue;
			}
			/**
			* 设置： 是否默认地址的值
			*/
			public void setIsdefaultValue(String isdefaultValue) {
				this.isdefaultValue = isdefaultValue;
			}
	//级联表的get和set 货物

		/**
		* 获取： 货物名称
		*/
		public String getHuowuName() {
			return huowuName;
		}
		/**
		* 设置： 货物名称
		*/
		public void setHuowuName(String huowuName) {
			this.huowuName = huowuName;
		}

		/**
		* 获取： 货物编号
		*/
		public String getHuowuUuidNumber() {
			return huowuUuidNumber;
		}
		/**
		* 设置： 货物编号
		*/
		public void setHuowuUuidNumber(String huowuUuidNumber) {
			this.huowuUuidNumber = huowuUuidNumber;
		}

		/**
		* 获取： 货物照片
		*/
		public String getHuowuPhoto() {
			return huowuPhoto;
		}
		/**
		* 设置： 货物照片
		*/
		public void setHuowuPhoto(String huowuPhoto) {
			this.huowuPhoto = huowuPhoto;
		}
		/**
		* 获取： 货物类型
		*/
		public Integer getHuowuTypes() {
			return huowuTypes;
		}
		/**
		* 设置： 货物类型
		*/
		public void setHuowuTypes(Integer huowuTypes) {
			this.huowuTypes = huowuTypes;
		}


			/**
			* 获取： 货物类型的值
			*/
			public String getHuowuValue() {
				return huowuValue;
			}
			/**
			* 设置： 货物类型的值
			*/
			public void setHuowuValue(String huowuValue) {
				this.huowuValue = huowuValue;
			}

		/**
		* 获取： 货物介绍
		*/
		public String getHuowuContent() {
			return huowuContent;
		}
		/**
		* 设置： 货物介绍
		*/
		public void setHuowuContent(String huowuContent) {
			this.huowuContent = huowuContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getHuowuDelete() {
			return huowuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setHuowuDelete(Integer huowuDelete) {
			this.huowuDelete = huowuDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "HuowuOrderView{" +
			", huowuOrderValue=" + huowuOrderValue +
			", addressName=" + addressName +
			", addressPhone=" + addressPhone +
			", addressDizhi=" + addressDizhi +
			", huowuName=" + huowuName +
			", huowuUuidNumber=" + huowuUuidNumber +
			", huowuPhoto=" + huowuPhoto +
			", huowuContent=" + huowuContent +
			", huowuDelete=" + huowuDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
