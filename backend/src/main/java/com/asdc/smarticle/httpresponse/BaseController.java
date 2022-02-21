package com.asdc.smarticle.httpresponse;

import org.springframework.http.HttpStatus;

/**
* Methods to send generalize Http response.  
* @author  Vivekkumar Patel
* @version 1.0
* @since   2022-02-19
*/
public class BaseController {

	protected <D> ResponseVO<D> prepareSuccessResponse(D data) {

		ResponseVO<D> responseVO = new ResponseVO<D>();
		responseVO.setData(data);
		responseVO.setStatusCode(HttpStatus.OK.value());
		responseVO.setMessage(HttpStatus.OK.name());
		responseVO.setStatus(true);
		responseVO.setData(data);
		return responseVO;
	}

	protected <D> ResponseVO<D> prepareErrorResponse(D data) {

		ResponseVO<D> responseVO = new ResponseVO<D>();
		responseVO.setData(data);
		responseVO.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseVO.setMessage(HttpStatus.BAD_REQUEST.name());
		responseVO.setStatus(false);
		responseVO.setData(data);
		return responseVO;
	}

	protected <D> ResponseVO<D> success(int statusCode, String message, boolean status) {

		ResponseVO<D> responseVO = new ResponseVO<D>();
		responseVO.setStatusCode(statusCode);
		responseVO.setStatus(status);
		responseVO.setMessage(message);
		return responseVO;
	}

	protected <D> ResponseVO<D> error(int statusCode, String message, boolean status) {

		ResponseVO<D> responseVO = new ResponseVO<D>();
		responseVO.setStatusCode(statusCode);
		responseVO.setStatus(status);
		responseVO.setMessage(message);
		return responseVO;
	}

}
