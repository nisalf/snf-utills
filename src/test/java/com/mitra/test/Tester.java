package com.mitra.test;

import org.junit.Assert;
import org.junit.Test;

import com.snfc.configs.Constants.ConnectionMethod;
import com.snfc.exceptions.SftpClientException;
import com.snfc.factory.SftpClient;
import com.snfc.factory.SftpClientFactory;
import com.snfc.objects.SftpKeyConfig;

public class Tester {

	@Test
	public void sftpTester() throws SftpClientException {
		SftpKeyConfig config = new SftpKeyConfig("192.168.200.101", "admin", 22);
		config.setSftpKeyPath("D:/temp/sftp keys/test");
		SftpClient client = new SftpClientFactory();
		client.getClient(ConnectionMethod.KEY, config);
		Assert.assertEquals(true, client.isDirectoryExists("com2com5"));

	}

}
