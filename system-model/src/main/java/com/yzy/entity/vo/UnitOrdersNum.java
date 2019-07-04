package com.yzy.entity.vo;

public class UnitOrdersNum {

	private Long unitId;
	private Integer num;
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "UnitOrdersNum [unitId=" + unitId + ", num=" + num + "]";
	}
}
