package cn.edu.zucc.personplan.util;

import java.io.*;
import java.nio.file.Files;

public class FileUtil {

    //    void writeFile(File srcFile,File dstFile) throws IOException {
    public static void writeFile(File srcFile, String dstFilePath) throws IOException {

        writeFile(srcFile, new File(dstFilePath));

    }

    public static void writeFile(String srcFilePath, String dstFilePath) throws IOException {

        writeFile(new File(srcFilePath), new File(dstFilePath));

    }

    public static String writeFileToDir(File srcFile, String dstDir) throws IOException {
        String name = srcFile.getName();
        String dstFilePath = dstDir + "/" + name;
        writeFile(srcFile, new File(dstFilePath));
        return dstFilePath;

    }


    public static void writeFile(File srcFile, File dstFile) throws IOException {

        FileInputStream fis = null;
//        InputStreamReader isr = null;
//        BufferedReader br = null;
        FileOutputStream fos = null;
//        OutputStreamWriter osw = null;
//        BufferedWriter bw = null;
//
//        FileReader fr = null;
//        FileWriter fw = null;

        if (srcFile == null) {
            return;
        }


//        JFileChooser dlg = new JFileChooser();
//        dlg.setDialogType(JFileChooser.SAVE_DIALOG);
//        dlg.showSaveDialog(null);

//        File getNewFile = dlg.getSelectedFile();

//        String getNewPath = getNewFile.getPath();
//        String getNewPath = dstFile.getPath();
//        String getNewName = getNewFile.getPath();
//        String getNewParent = getNewFile.getParent();
//
//        System.out.println("getNewPath::" + getNewPath);
//        System.out.println("getNewName::" + getNewName);
//        System.out.println("getNewParent::" + getNewParent);
        //写入新文件

        try {
            //读源文件
//            fis = new FileInputStream(this.targetFile);
            fis = new FileInputStream(srcFile);
//            isr = new InputStreamReader(fis);
//            br = new BufferedReader(isr);

//            fr = new FileReader(this.targetFile);
//            fr = new FileReader(srcFile);
            //写新文件
//			File newFile = new File(dstFilePath);
            if (!dstFile.exists()) {
                dstFile.createNewFile();
            }
            fos = new FileOutputStream(dstFile);
//            osw = new OutputStreamWriter(fos);
//            bw = new BufferedWriter(osw);
//            fw = new FileWriter(newFile);

            //下面提供了4中读写文件方法，都已通过测试。

            //用BufferedReader，BufferedWriter 实验成功,注意读一行后加换行符！
//				String ss=null;
//				while((ss=br.readLine())!=null){
//					bw.write(ss+"\n");
//					bw.flush();
//				}

            //用InputStreamReader，OutputStreamWriter 实验成功
//				char[] buf=new char[2048];
//				int num=0;
//				while((num=isr.read(buf))!=(-1))
//				{ //是否读完文件
//					osw.write(buf, 0, num);//把文件数据写出网络缓冲区
//					osw.flush();//刷新缓冲区把数据写往客户端
//				}


            //用FileReader,FileWriter操作，试验成功
//				char[] cbuf=new char[1024];
//				int nn=0;
//				while((nn=fr.read(cbuf))!=-1){
//					bw.write(cbuf,0,nn);
//					bw.flush();
//			    }

            //用FileInputStream,FileOutputStream操作，试验成功
//            byte[] b = new byte[512];
            byte[] b = new byte[1024];
//            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                fos.write(n);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

//            if (bw != null) {
//                bw.close();
//            }
//            if (osw != null) {
//                osw.close();
//            }

            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
//            if (br != null) {
//                br.close();
//            }
//            if (isr != null) {
//                isr.close();
//            }
//
//            if (fr != null) {
//                fr.close();
//            }
//            if (fw != null) {
//                fw.close();
//            }

        }
//        JOptionPane.showMessageDialog(null, "保存成功！");
    }

    public static void copyToDir(String src, String dstDir) throws IOException {
        File srcFile = new File(src);
        String name = srcFile.getName();
        String dstFile = dstDir + "/" + name;
        Files.copy(srcFile.toPath(), new File(dstFile).toPath());
    }

    public static String  copyToDir(File srcFile, String dstDir) throws IOException {
//        File srcFile = new File(src);
        String name = srcFile.getName();
        String dstFile = dstDir + "/" + name;
        Files.copy(srcFile.toPath(), new File(dstFile).toPath());
        return  dstFile;
    }

    public static void copy(String src, String dst) throws IOException {
        Files.copy(new File(src).toPath(), new File(dst).toPath());
    }

    public static void copy(File src, File dst) throws IOException {
        Files.copy(src.toPath(), dst.toPath());
    }

    public static void main(String[] args) throws IOException {
//        writeFileToDir(new File("G:\\edgeDownload\\longzerora.jpg"),"G:\\file\\img");
        Files.copy(new File("G:\\edgeDownload\\longzerora.jpg").toPath(),
                new File("G:\\file\\img\\11.jpg").toPath());
    }

}
