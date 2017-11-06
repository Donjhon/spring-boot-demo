package com.boot.exception;

import org.springframework.dao.DataAccessException;

public class TranException extends DataAccessException {

	private static final long serialVersionUID = 8901479830692029025L;

	public TranException(String msg) {
		super(msg);
	}
}