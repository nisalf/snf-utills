package com.snfc.factory;

import com.snfc.configs.Constants.ConnectionMethod;
import com.snfc.exceptions.SftpClientException;
import com.snfc.objects.SftpKeyConfig;

public interface SftpClient {

	public SftpClient getClient(ConnectionMethod method, SftpKeyConfig config) throws SftpClientException;

	public boolean isDirectoryExists(String directory) throws SftpClientException;

}
