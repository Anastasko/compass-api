package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="File View Model", description = "on disk file view model", show = false)
public class FileInfo {

    @ApiObjectField
    private String path;

    @ApiObjectField
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
