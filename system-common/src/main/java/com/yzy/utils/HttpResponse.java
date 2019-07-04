package com.yzy.utils;

import org.apache.http.Header;

public class HttpResponse {
	
	private Header[] headers;
	private String body;
	private String reasonPhrase;
	private int statusCode;
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "HttpResponse [headers=" + headers + ", body=" + body + ", reasonPhrase=" + reasonPhrase
				+ ", statusCode=" + statusCode + "]";
	}
	
}
