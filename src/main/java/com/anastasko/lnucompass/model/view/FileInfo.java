package com.anastasko.lnucompass.model.view;

public class FileInfo {

    private String path;

    private long modified;

    public FileInfo(String path, long m) {
        setPath(path);
        setModified(m);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }
}
