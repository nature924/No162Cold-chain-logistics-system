package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.KuaidiyuanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 快递员
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("kuaidiyuan")
public class KuaidiyuanView extends KuaidiyuanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 性别的值
	*/
	@ColumnInfo(comment="性别的字典表值",type="varchar(200)")
	private String sexValue;

	//级联表 网点信息
		/**
		* 网点名称
		*/

		@ColumnInfo(comment="网点名称",type="varchar(200)")
		private String wangdianName;
		/**
		* 联系人姓名
		*/

		@ColumnInfo(comment="联系人姓名",type="varchar(200)")
		private String lianxirenName;
		/**
		* 联系人手机号
		*/

		@ColumnInfo(comment="联系人手机号",type="varchar(200)")
		private String lianxirenPhone;
		/**
		* 地址
		*/

		@ColumnInfo(comment="地址",type="varchar(200)")
		private String lianxirenAddress;
		/**
		* 网点类型
		*/
		@ColumnInfo(comment="网点类型",type="int(11)")
		private Integer wangdianTypes;
			/**
			* 网点类型的值
			*/
			@ColumnInfo(comment="网点类型的字典表值",type="varchar(200)")
			private String wangdianValue;
		/**
		* 网点详情
		*/

		@ColumnInfo(comment="网点详情",type="longtext")
		private String wangdianContent;



	public KuaidiyuanView() {

	}

	public KuaidiyuanView(KuaidiyuanEntity kuaidiyuanEntity) {
		try {
			BeanUtils.copyProperties(this, kuaidiyuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 性别的值
	*/
	public String getSexValue() {
		return sexValue;
	}
	/**
	* 设置： 性别的值
	*/
	public void setSexValue(String sexValue) {
		this.sexValue = sexValue;
	}


	//级联表的get和set 网点信息

		/**
		* 获取： 网点名称
		*/
		public String getWangdianName() {
			return wangdianName;
		}
		/**
		* 设置： 网点名称
		*/
		public void setWangdianName(String wangdianName) {
			this.wangdianName = wangdianName;
		}

		/**
		* 获取： 联系人姓名
		*/
		public String getLianxirenName() {
			return lianxirenName;
		}
		/**
		* 设置： 联系人姓名
		*/
		public void setLianxirenName(String lianxirenName) {
			this.lianxirenName = lianxirenName;
		}

		/**
		* 获取： 联系人手机号
		*/
		public String getLianxirenPhone() {
			return lianxirenPhone;
		}
		/**
		* 设置： 联系人手机号
		*/
		public void setLianxirenPhone(String lianxirenPhone) {
			this.lianxirenPhone = lianxirenPhone;
		}

		/**
		* 获取： 地址
		*/
		public String getLianxirenAddress() {
			return lianxirenAddress;
		}
		/**
		* 设置： 地址
		*/
		public void setLianxirenAddress(String lianxirenAddress) {
			this.lianxirenAddress = lianxirenAddress;
		}
		/**
		* 获取： 网点类型
		*/
		public Integer getWangdianTypes() {
			return wangdianTypes;
		}
		/**
		* 设置： 网点类型
		*/
		public void setWangdianTypes(Integer wangdianTypes) {
			this.wangdianTypes = wangdianTypes;
		}


			/**
			* 获取： 网点类型的值
			*/
			public String getWangdianValue() {
				return wangdianValue;
			}
			/**
			* 设置： 网点类型的值
			*/
			public void setWangdianValue(String wangdianValue) {
				this.wangdianValue = wangdianValue;
			}

		/**
		* 获取： 网点详情
		*/
		public String getWangdianContent() {
			return wangdianContent;
		}
		/**
		* 设置： 网点详情
		*/
		public void setWangdianContent(String wangdianContent) {
			this.wangdianContent = wangdianContent;
		}


	@Override
	public String toString() {
		return "KuaidiyuanView{" +
			", sexValue=" + sexValue +
			", wangdianName=" + wangdianName +
			", lianxirenName=" + lianxirenName +
			", lianxirenPhone=" + lianxirenPhone +
			", lianxirenAddress=" + lianxirenAddress +
			", wangdianContent=" + wangdianContent +
			"} " + super.toString();
	}
}
