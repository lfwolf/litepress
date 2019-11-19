package cn.stylefeng.guns.core.common.model;

import lombok.Data;

@Data
public class ResponseResult {
	private int code;
	private String msg;
	private Object data;
	private Object info;
}
