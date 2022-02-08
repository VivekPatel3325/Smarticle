package com.asdc.smarticle.httpresponse;

import java.util.ArrayList;
import java.util.List;

public class ResponseVO<D> {
	
	private D data;
	private String message;
	private boolean status;
	private String statusCode;
	private String errorCode;
	private List<Error> errors=new ArrayList();
	
	
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
	
	
	
}
