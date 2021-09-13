package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.FileDto;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

//import org.dyno.visual.swing.layouts.Constraints;
//import org.dyno.visual.swing.layouts.GroupLayout;
//import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!

/**
 * @author lushuaiyin
 * @date 2011-10-18
 */
//https://blog.csdn.net/lushuaiyin/article/details/84050330
public class FrmSelFile extends JFrame {

    File targetFile;
    String message = "";

    private static final long serialVersionUID = 1L;
    private JTextArea jTextArea0;
    private JScrollPane jScrollPane0;
    private JButton jButton0;
    private JLabel jLabel1;
    private JButton jButton1;
    private JButton jButton2;
    private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

    public FrmSelFile() {
        initComponents();
    }

    FileDto fileDto;

    //	需要知道选择的 file是 什么 ， 然后可以转移文件 应该没问题
    public FrmSelFile(FileDto fileDto) {
        this.fileDto = fileDto;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		setLayout(new GroupLayout());
//		setLayout(new GridLayout());
//		add(getJLabel1(), new SpringLayout.Constraints(new Leading(12, 12, 12), new Leading(59, 22, 10, 10)));
//		add(getJButton1(), new Constraints(new Leading(52, 10, 10), new Leading(142, 10, 10)));
//		add(getJButton0(), new Constraints(new Leading(218, 10, 10), new Leading(142, 12, 12)));
//		add(getJScrollPane0(), new Constraints(new Leading(106, 334, 10, 10), new Leading(42, 51, 10, 10)));
//		add(getJButton2(), new Constraints(new Leading(329, 10, 10), new Leading(142, 12, 12)));

        JPanel pnlPath = new JPanel();
        pnlPath.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlPath.add(getJLabel1());
        pnlPath.add(getJScrollPane0());

        add(pnlPath, BorderLayout.NORTH);
//		add(getJLabel1());
//		add(getJScrollPane0());

        JPanel pnlBtn = new JPanel();
        pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBtn.add(getJButton0());
        pnlBtn.add(getJButton1());
        pnlBtn.add(getJButton2());
//		add(getJButton1());
//		add(getJButton0());
//		add(getJButton2());
        add(pnlBtn, BorderLayout.CENTER);
        setSize(501, 240);
    }

    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("（图片时）另存为");
            jButton2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {
                    try {
                        jButton2ActionActionPerformed(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
        return jButton2;
    }

    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setText("选择读取文件");
            jButton1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {
                    jButton1ActionActionPerformed(event);
                }
            });
        }
        return jButton1;
    }

    private JLabel getJLabel1() {
        if (jLabel1 == null) {
            jLabel1 = new JLabel();
            jLabel1.setText("源文件路径");
        }
        return jLabel1;
    }

    private JButton getJButton0() {
        if (jButton0 == null) {
            jButton0 = new JButton();
            jButton0.setText("另存为");
            jButton0.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent event) {
                    try {
                        jButton0ActionActionPerformed(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
        return jButton0;
    }

    private JScrollPane getJScrollPane0() {
        if (jScrollPane0 == null) {
            jScrollPane0 = new JScrollPane();
            jScrollPane0.setViewportView(getJTextArea0());
        }
        return jScrollPane0;
    }

    private JTextArea getJTextArea0() {
        if (jTextArea0 == null) {
            jTextArea0 = new JTextArea();
            jTextArea0.setText("jTextArea0");
        }
        return jTextArea0;
    }

    private static void installLnF() {
        try {
            String lnfClassname = PREFERRED_LOOK_AND_FEEL;
            if (lnfClassname == null)
                lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(lnfClassname);
        } catch (Exception e) {
            System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
                    + " on this platform:" + e.getMessage());
        }
    }

    /**
     * Main entry of the class.
     * Note: This class is only created so that you can easily preview the result at runtime.
     * It is not expected to be managed by the designer.
     * You can modify it as you like.
     */
    public static void main(String[] args) {
        installLnF();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmSelFile frame = new FrmSelFile();
                frame.setDefaultCloseOperation(FrmSelFile.EXIT_ON_CLOSE);
                frame.setTitle("Settest");
                frame.getContentPane().setPreferredSize(frame.getSize());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    //读取文件
    private void jButton1ActionActionPerformed(ActionEvent event) {
        JFileChooser dlg = new JFileChooser();
        /**
         * 设置对话框类型,其打开方法也要对应正确，打开方法控制显示界面
         */
        dlg.setDialogType(JFileChooser.OPEN_DIALOG);
        //设定好类型后和dlg.showOpenDialog(null)方法配套使用才会显示保存对话框
//		dlg.setDialogType(JFileChooser.SAVE_DIALOG);
        //设定好类型后和dlg.showDialog(null, "haha")方法配套使用，并且第一个按钮显示为第二个参数"haha".
//		dlg.setDialogType(JFileChooser.CUSTOM_DIALOG);


        //第一个参数父组件，第2 个参数是第一个按钮的名字（不再是打开或保存了）
//		dlg.showDialog(null, "haha");
        dlg.showOpenDialog(null);
//		dlg.showSaveDialog(null);

        File file = dlg.getSelectedFile();
        if (file == null) {
            this.message = "没有选取文件";
        } else {
            //得到这个文件后，想干嘛干嘛
            System.out.println("getAbsolutePath--" + file.getAbsolutePath());
            System.out.println("getName--" + file.getName());
            System.out.println("isFile--" + file.isFile());
            System.out.println("isDirectory--" + file.isDirectory());
            System.out.println("getPath--" + file.getPath());
            System.out.println("getParent--" + file.getParent());

            this.targetFile = file;
            this.message = file.getPath().toString();

            fileDto.setFile(file);

        }
        jTextArea0.setText(this.message);
    }

    //另存为
    private void jButton0ActionActionPerformed(ActionEvent event) throws IOException {

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        FileReader fr = null;
        FileWriter fw = null;

        if (targetFile == null) {
            System.out.println("源文件为空，请先选择要保存的文件！");
            JOptionPane.showMessageDialog(null, "源文件为空，请先选择要保存的文件！");

        } else {
            JFileChooser dlg = new JFileChooser();
            dlg.setDialogType(JFileChooser.SAVE_DIALOG);
            dlg.showSaveDialog(null);

            File getNewFile = dlg.getSelectedFile();

            String getNewPath = getNewFile.getPath();
            String getNewName = getNewFile.getPath();
            String getNewParent = getNewFile.getParent();

            System.out.println("getNewPath::" + getNewPath);
            System.out.println("getNewName::" + getNewName);
            System.out.println("getNewParent::" + getNewParent);
            //写入新文件

            try {
                //读源文件
                fis = new FileInputStream(this.targetFile);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);

                fr = new FileReader(this.targetFile);
                //写新文件
                File newFile = new File(getNewPath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                fos = new FileOutputStream(newFile);
                osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);
                fw = new FileWriter(newFile);

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
                byte[] b = new byte[512];
                int n;
                while ((n = fis.read(b)) != -1) {
                    fos.write(n);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }

                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }

            }
            JOptionPane.showMessageDialog(null, "保存成功！");
        }
    }

    //    void writeFile(File srcFile,File dstFile) throws IOException {
    void writeFile(File srcFile, String dstFilePath) throws IOException {

    	writeFile(srcFile,new File(dstFilePath));

    }

	void writeFile(String  srcFilePath, String dstFilePath) throws IOException {

		writeFile(new File(srcFilePath),new File(dstFilePath));

	}


	void writeFile(File srcFile, File dstFile) throws IOException {

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
			byte[] b = new byte[512];
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

    //图片时另存为
    private void jButton2ActionActionPerformed(ActionEvent event) throws IOException {

        FileInputStream fis2 = null;
        InputStreamReader isr2 = null;
        BufferedReader br2 = null;
        FileOutputStream fos2 = null;
        OutputStreamWriter osw2 = null;
        BufferedWriter bw2 = null;

        FileReader fr2 = null;
        FileWriter fw2 = null;


        JFileChooser dlg = new JFileChooser();
        dlg.setDialogType(JFileChooser.SAVE_DIALOG);
        dlg.showSaveDialog(null);

        File getNewFile = dlg.getSelectedFile();

        String getNewPath = getNewFile.getPath();
        String getNewName = getNewFile.getPath();
        String getNewParent = getNewFile.getParent();

        System.out.println("getNewPath::" + getNewPath);
        System.out.println("getNewName::" + getNewName);
        System.out.println("getNewParent::" + getNewParent);
        //写入新文件

        try {
            //读源文件
            fis2 = new FileInputStream(this.targetFile);
            isr2 = new InputStreamReader(fis2);
            br2 = new BufferedReader(isr2);
            Image src = javax.imageio.ImageIO.read(this.targetFile); //构造Image对象
            int wideth = src.getWidth(null); //得到源图宽
            int height = src.getHeight(null); //得到源图长

            fr2 = new FileReader(this.targetFile);
            //写新文件
            File newFile = new File(getNewPath);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            fos2 = new FileOutputStream(newFile);
            osw2 = new OutputStreamWriter(fos2);
            bw2 = new BufferedWriter(osw2);
            fw2 = new FileWriter(newFile);

            //用Graphics操作，试验成功
            BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src, 0, 0, wideth, height, null);
            fos2 = new FileOutputStream(getNewPath); //输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos2);
            encoder.encode(tag); //JPEG编码

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bw2 != null) {
                bw2.close();
            }
            if (osw2 != null) {
                osw2.close();
            }
            if (fos2 != null) {
                fos2.close();
            }
            if (br2 != null) {
                br2.close();
            }
            if (isr2 != null) {
                isr2.close();
            }

            if (fr2 != null) {
                fr2.close();
            }
            if (fw2 != null) {
                fw2.close();
            }

        }
        JOptionPane.showMessageDialog(null, "保存成功！");

    }

}
 
 