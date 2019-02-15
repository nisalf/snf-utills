package com.snfc.exceptions;

import com.jcraft.jsch.JSchException;

public class SftpClientException extends JSchException{
	
	private static final long serialVersionUID = 1L;

	public SftpClientException(String errorMessage) {
		super(errorMessage);
	}

}
