package cn.edu.zucc.personplan.model;

import java.io.File;

public class FileDto {
    String picPath;
    File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileDto(String picPath) {
        this.picPath = picPath;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public FileDto() {
    }



    @Override
    public String toString() {
        return "FileDto{" +
                "picPath='" + picPath + '\'' +
                '}';
    }
}
