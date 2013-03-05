package com.jfinal.ext.rapid.kit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class JarKit {

    public static void main(String args[]) {
        JarKit.compress("/home/kid/git/jfinal-ext/build/jfinal-ext-1.3-b20130116-1.6.jar", "/home/kid/testjar");
    }

    /**
     * 从jar中匹配符合条件的class打包成新的jar
     * 
     * @param jar
     * @param pattern 正则表达式
     */
    public static void extract(String jar, String pattern) {
        // TODO
    }

    /**
     * 解压jar包到指定的目录
     * 
     * @param jarFileName
     * @param outputPath
     */
    public static void compress(String jarFileName, String outputPath) {
        try {
            decompress(jarFileName, outputPath);
        } catch (IOException e) {
            e.printStackTrace();
            dealError(outputPath);
            return;
        }
    }

    /**
     * 生成jar
     * 
     * @param resDir
     * @param jar
     * @throws IOException
     */
    public static void createJarFile(File resDir, File jar) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(jar);
        File dir = jar.getParentFile();
        if (!dir.exists()) dir.mkdirs();

        CheckedOutputStream cs = new CheckedOutputStream(fileOutputStream, new CRC32());
        Manifest man = new Manifest();
        FileInputStream in = new FileInputStream(new File(resDir.getAbsoluteFile(), JarFile.MANIFEST_NAME));
        man.read(in);
        in.close();
        JarOutputStream out = new JarOutputStream(cs, man);
        addDirectory(out, resDir, resDir.getAbsolutePath());
        out.close();
    }

    /**
     * 解压缩JAR包
     * 
     * @param fileName 文件名
     * @param outputPath 解压输出路径
     * @throws IOException IO异常
     */
    private static void decompress(String fileName, String outputPath) throws IOException {
        if (!outputPath.endsWith(File.separator)) {
            outputPath += File.separator;
        }
        JarFile jf = new JarFile(fileName);
        for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements();) {
            JarEntry je = e.nextElement();
            String outFileName = outputPath + je.getName();
            File f = new File(outFileName);
            // 创建该路径的目录和所有父目录
            makeSupDir(outFileName);
            // 如果是目录，则直接进入下一个循环
            if (f.isDirectory()) {
                continue;
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = jf.getInputStream(je);
                out = new BufferedOutputStream(new FileOutputStream(f));
                byte[] buffer = new byte[2048];
                int nBytes = 0;
                while ((nBytes = in.read(buffer)) > 0) {
                    out.write(buffer, 0, nBytes);
                }
            } catch (IOException ioe) {
                throw ioe;
            } finally {
                try {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                    if (null != jf) {
                        jf.close();
                    }
                } catch (IOException ioe) {
                    throw ioe;
                } finally {
                    if (null != in) {
                        in.close();
                    }
                }
            }
        }
    }

    /**
     * 循环创建父目录
     * 
     * @param outFileName
     */
    private static void makeSupDir(String outFileName) {
        // 匹配分隔符
        Pattern p = Pattern.compile("[/\\" + File.separator + "]");
        Matcher m = p.matcher(outFileName);
        // 每找到一个匹配的分隔符，则创建一个该分隔符以前的目录
        while (m.find()) {
            int index = m.start();
            String subDir = outFileName.substring(0, index);
            File subDirFile = new File(subDir);
            if (!subDirFile.exists()) subDirFile.mkdir();
        }
    }

    /**
     * 递归删除目录及子目录
     * 
     * @param path
     */
    private static void clean(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path + " Not Exist!");
        } else {
            // 如果是目录，则递归删除
            if (file.isDirectory()) {
                String[] fileNames = file.list();
                if (null == fileNames) {
                    throw new IOException("IO ERROR While Deleting Files");
                }
                // 如果是空目录则直接删除
                else if (fileNames.length == 0) {
                    file.delete();
                } else {
                    for (String fileName : fileNames) {
                        File subFile = new File(fileName);
                        clean(path + File.separator + subFile);
                    }
                    System.out.println(file.getAbsolutePath());
                    // 最后删除父目录
                    file.delete();

                }
            }
            // 如果是文件，则直接删除
            else {
                file.delete();
            }
        }
    }

    /**
     * 处理安装错误的异常，调用清洗过程
     * 
     * @param outputPath 输出路径
     * @throws IOException IO异常
     */
    private static void dealError(String outputPath) {
        try {
            clean(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addDirectory(JarOutputStream out, File dir, String root) throws IOException {
        String entryName = dir.getAbsolutePath().replace(root, "").replace('\\', '/');
        entryName = entryName.replaceFirst("^/", "");
        JarEntry entry = new JarEntry(entryName + "/");
        out.putNextEntry(entry);
        out.closeEntry();
        String[] files = dir.list();
        FileInputStream in = null;
        byte[] buff = new byte[1024 * 64];
        int length = 0;
        for (String sFile : files) {
            File file = new File(dir, sFile);
            if (file.isDirectory()) {
                addDirectory(out, file, root);
            } else if (!"MANIFEST.MF".endsWith(file.getName())) {
                String eName = entryName + "/" + file.getName();
                eName = eName.replaceFirst("^/", "");
                entry = new JarEntry(eName);
                in = new FileInputStream(file);
                out.putNextEntry(entry);
                while ((length = in.read(buff)) > -1) {
                    out.write(buff, 0, length);
                    out.flush();
                }
                out.closeEntry();
                in.close();
            }
        }
    }

}
