package com.snfc.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.awaitility.Awaitility;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.snfc.configs.Constants.ConnectionMethod;
import com.snfc.exceptions.SftpClientException;
import com.snfc.objects.SftpKeyConfig;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SftpClientFactory implements SftpClient {
	private Session session;
	private ChannelSftp sftpChannel;

	public SftpClient getClient(ConnectionMethod method, SftpKeyConfig config) throws SftpClientException {
		try {
			session = SftpAdapter.getSessionByKeyFile(config);
			Awaitility.await().atMost(20, SECONDS).pollInterval(2, SECONDS).until(connect());
			sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
		} catch (JSchException ex) {
			throw new SftpClientException("");
		}
		return this;
	}

	public boolean isDirectoryExists(String directory) throws SftpClientException {
		return getFileNames(directory).contains(directory);
	}

	private Callable<Boolean> connect() {
	      return new Callable<Boolean>() {
	            public Boolean call() {
	            	try{
		            	session.connect();
		            	return true;
	            	} catch (Exception ex) {
	            		return false;
	            	}
	            }
	      };
	}

	private List<String> getFileNames(final String directory) throws SftpClientException {
		List<String> filelist = new ArrayList<String>();
		try {
			@SuppressWarnings("unchecked")
			Vector<ChannelSftp.LsEntry> lsEntries = sftpChannel.ls("/");
			for (ChannelSftp.LsEntry lsEntry : lsEntries) {
				String filename = lsEntry.getFilename();
				if (!".".equals(filename) && !"..".equals(filename) && lsEntry.getAttrs().isDir()) {
					filelist.add(filename);
				}
			}
		} catch (final Exception except) {
			throw new SftpClientException(except.getMessage());
		}
		finally{session.disconnect();sftpChannel.disconnect();}
		return filelist;
	}

}
