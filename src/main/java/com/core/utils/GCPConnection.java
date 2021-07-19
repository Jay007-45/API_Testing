package com.core.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GCPConnection implements IGCPConnection {

    private static GCPConnection instance;

    Storage storage;

    private GCPConnection() throws IOException {

        storage = StorageOptions.newBuilder()
                .setProjectId("") //projectId
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("/src/test/a.json")))//serviceAccountJSON
                .build()
                .getService();

    }

    public static GCPConnection getInstance() throws IOException {
        if (instance == null) {
            instance = new GCPConnection();
        }
        return instance;
    }

    public Storage getConnection() {
        return storage;
    }

    @Override
    public boolean isObjectExist(String filename) {
        Blob blob = storage.get("", "");
        if (blob != null && blob.exists()) {
            return true;
        }
        return false;
    }


    @Override
    public File getFile(String filepath) {
        return null;
    }

    @Override
    public boolean copyFile(String src, String destination) {
        return false;
    }

}
