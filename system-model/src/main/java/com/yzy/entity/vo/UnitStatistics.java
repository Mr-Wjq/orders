package com.yzy.entity.vo;

public class UnitStatistics {

	private String unitName;
	private Integer zhengji;
	private Integer zhongzhi;
	private Integer xiufu;
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getZhengji() {
		return zhengji;
	}
	public void setZhengji(Integer zhengji) {
		this.zhengji = zhengji;
	}
	public Integer getZhongzhi() {
		return zhongzhi;
	}
	public void setZhongzhi(Integer zhongzhi) {
		this.zhongzhi = zhongzhi;
	}
	public Integer getXiufu() {
		return xiufu;
	}
	public void setXiufu(Integer xiufu) {
		this.xiufu = xiufu;
	}
	@Override
	public String toString() {
		return "UnitStatistics [unitName=" + unitName + ", zhengji=" + zhengji + ", zhongzhi=" + zhongzhi + ", xiufu="
				+ xiufu + "]";
	}
}
