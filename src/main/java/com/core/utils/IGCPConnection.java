package com.core.utils;

import java.io.File;

public interface IGCPConnection {

   boolean isObjectExist(String filename);
   File getFile(String filepath);
   boolean copyFile(String src,String destination);

}
