package com.depromeet.finddepro.network;

public enum ResponseCode {
	// @TODO : (jonghyo) 만약 http 코드 외에 다른 상태코드가 필요한 경우 이 곳에 추가해서 쓸 것
	SUCCESS("0000"),
	UNKNOWN_SYSTEM_ERROR("9999");

	private final String code;

	ResponseCode(String code) {
		this.code = code;
	}

	public boolean equals(String code) {
		return this.code.equals(code);
	}

	public String getCode() {
		return code;
	}
}
