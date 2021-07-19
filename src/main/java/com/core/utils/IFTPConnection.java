package com.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.jcraft.jsch.SftpException;

public interface IFTPConnection {

	public File getFile(String file) throws IOException;
	public List<String> getAllFilenames(String filepath) throws SftpException;
}
