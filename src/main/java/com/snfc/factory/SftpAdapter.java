package com.snfc.factory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.snfc.exceptions.SftpClientException;
import com.snfc.objects.SftpConfig;
import com.snfc.objects.SftpKeyConfig;

public class SftpAdapter {
	private static JSch jsch = new JSch();
	private static Session session;

	public static Session getSessionByKeyFile(SftpKeyConfig config) throws SftpClientException {
		try {
			final java.util.Properties prop = new java.util.Properties();
			prop.put("StrictHostKeyChecking", "no");
			session = jsch.getSession(config.getSftpUser(), config.getSftpHost(), config.getSftpPort());
			session.setConfig(prop);
			jsch.addIdentity(config.getSftpKeyPath());
		} catch (JSchException ex) {
			throw new SftpClientException(ex.getMessage());
		}
		return session;
	}

	// to be implemented
	public static Session getSessionByPassword(SftpConfig config) throws SftpClientException {
		try {
			session = jsch.getSession(config.getSftpUser(), config.getSftpHost(), config.getSftpPort());
			session.setPassword("");
		} catch (JSchException ex) {
			throw new SftpClientException(ex.getMessage());
		}
		return session;
	}

}
