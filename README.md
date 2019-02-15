# snf-utills

#sftp client 

How to use?

		SftpKeyConfig config = new SftpKeyConfig("192.168.200.101", "admin", 22);
		config.setSftpKeyPath("D:/temp/sftp keys/test");
		SftpClient client = new SftpClientFactory();
		client.getClient(ConnectionMethod.KEY, config);
