package com.yzy.utils;

import java.util.List;

public class LayuiTable {

	private Integer code = 200;
	private String msg = "获取数据成功";
	private Long total;
	private List<?> rows;
	public LayuiTable(Long total,List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public LayuiTable(String msg,Long total,List<?> rows) {
		this.msg = msg;
		this.total = total;
		this.rows = rows;
	}
	public LayuiTable(Integer code,String msg,Long total,List<?> rows) {
		this.code = code;
		this.msg = msg;
		this.total = total;
		this.rows = rows;
	}
	public static LayuiTable success(Long total,List<?> rows) {
        return new LayuiTable(total, rows);
    }
	public static LayuiTable error(Integer code,String msg) {
		return new LayuiTable(code, msg,(long)0,null);
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "LayuiTable [code=" + code + ", msg=" + msg + ", total=" + total + ", rows=" + rows + "]";
	}
	
}
