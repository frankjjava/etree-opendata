/**
* Copyright © 2020 eTree Technologies Pvt. Ltd.
*
* @author  Franklin Joshua
* @version 1.0
* @since   2020-11-04 
*/
package etree.refdata.common.exception;

import com.etree.commons.core.exception.EtreeCommonsException;

public class RefdataException extends EtreeCommonsException {

	private static final long serialVersionUID = 5477607325912585120L;
	
	public RefdataException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}

	public RefdataException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	public RefdataException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public RefdataException(String errorCode) {
		super(errorCode);
	}

	public RefdataException(Throwable cause) {
		super(cause);
	}
}