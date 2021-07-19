package com.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class FTPConnection implements IFTPConnection{

	public String hostname;
	public String port;
	public String username;
	public String password;

	public Session session = null;
	public Channel channel = null;
	ChannelSftp channelSftp = null;
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;

	private static FTPConnection instance;

	private FTPConnection() {
		try {

//			hostname = FtpDTO.getInstance().getHostname();
//			port = FtpDTO.getInstance().getPort();
//			username = FtpDTO.getInstance().getUsername();
//			password = FtpDTO.getInstance().getPassword();

			JSch jsch = new JSch();
			session = jsch.getSession(username, hostname, Integer.parseInt(port));
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(30000);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public ChannelSftp getConnection() {
		return channelSftp;

	}

	public static FTPConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new FTPConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new FTPConnection();
		}

		return instance;
	}

	@Override
	public File getFile(String file) throws IOException {

		Path p = Paths.get(file);
		String targetfile = p.getFileName().toString();

		try {
			bis = new BufferedInputStream(channelSftp.get(file));
		} catch (SftpException e) {

			e.printStackTrace();
//			Logger.error("Error occured during FTP connection :" + e.getLocalizedMessage());
		}

		File newFile = new File("src/test/resources/data/dump/" + targetfile);
		OutputStream os = new FileOutputStream(newFile);
		bos = new BufferedOutputStream(os);

		byte[] buffer = new byte[1024];
		int readCount;
		while ((readCount = bis.read(buffer)) > 0) {
			System.out.println("Copying file "+targetfile+" from FTP server...");
			bos.write(buffer, 0, readCount);
		}
		bis.close();
		bos.close();
		return newFile;
	}

	@Override
	public List<String> getAllFilenames(String filelocation) throws SftpException {
		List<String> fileNames = null;
		channelSftp.cd(filelocation);
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.csv");
		for(ChannelSftp.LsEntry entry : list) {
		     System.out.println(entry.getFilename()); 
		     fileNames.add(entry.getFilename());
		}
		return fileNames;
	}

}
