/**
* Copyright © 2016 Verteil Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2016-01-15 
*/
package com.etree.opendata.common.exception;

import com.etree.commons.core.exception.EtreeCommonsException;

public class OpendataException extends EtreeCommonsException {

	private static final long serialVersionUID = 5477607325912585120L;
	
	public OpendataException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}

	public OpendataException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	public OpendataException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public OpendataException(String errorCode) {
		super(errorCode);
	}

	public OpendataException(Throwable cause) {
		super(cause);
	}
}