package com.snfc.objects;

public class SftpKeyConfig extends SftpConfig{
	
	private String sftpKeyPath;
	
	public SftpKeyConfig(String host, String user, int port) {
		super.setSftpHost(host);
		super.setSftpUser(user);
		super.setSftpPort(port);
	}

	public String getSftpKeyPath() {
		return sftpKeyPath;
	}

	public void setSftpKeyPath(String sftpKeyPath) {
		this.sftpKeyPath = sftpKeyPath;
	}
	
	

}
