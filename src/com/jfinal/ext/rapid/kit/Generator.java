package com.jfinal.ext.rapid.kit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import bsh.EvalError;

import com.jfinal.ext.rapid.designer.Task;
import com.jfinal.ext.rapid.designer.project.Project;
import com.jfinal.ext.rapid.kit.dialect.MysqlDialect;
import com.jfinal.kit.PathKit;
import com.jfinal.log.Logger;

public class Generator {

    protected final Logger logger = Logger.getLogger(getClass());

    private Project        project;

    private File           srcDir;

    private File           webRootDir;

    private File           libDir;

    private File           commonLibDir;

    private File           diffDir;

    private File           diffLibDir;

    private File           dbdriverDir;

    private File           dbpoolDir;

    private File           commonDir;

    private File           classesDir;

    public Generator(Project project){
        this.project = project;
    }

    public void start() throws Exception {
        init();
        runTasks();
    }

    /**
     * 执行所有生成任务
     * 
     * @throws IOException
     */
    private void runTasks() throws EvalError {
        System.out.println( project.getEntities());
        List<Task> tasks = TaskKit.createTaskls();
        for (Task task : tasks) {
            TaskKit.processTask(project, project.getEntities(), task);
        }
    }


    public void init() throws Exception {
        setDir();
        clean();
        copyCommon();
        copyDbDriver();
        copyDbPool();
        copyViewFramework();
    }

    private void setDir() {
        srcDir = new File(project.get("workspace") + File.separator + project.get("name") + File.separator
                          + project.get("src"));
        webRootDir = new File(project.get("workspace") + File.separator + project.get("name") + File.separator
                              + project.get("webRoot"));
        libDir = new File(webRootDir, "WEB-INF/lib");

        commonDir = new File(PathKit.getRootClassPath() + File.separator + "common");
        diffDir = new File(PathKit.getRootClassPath() + File.separator + "diff");

        commonLibDir = new File(commonDir, "lib");
        diffLibDir = new File(diffDir, "lib");
        dbdriverDir = new File(diffLibDir, "dbdriver/" + project.get("dbType"));
        dbpoolDir = new File(diffLibDir, "dbpool/" + project.get("dbPool"));

        classesDir = new File(webRootDir, "/WEB-INF" + File.separator + "classes");

    }

    private void clean() throws IOException {

        if (project.checkEmbedded()) {
            File classesTemp = new File(webRootDir, File.separator + "classes_temp");
            FileUtils.copyDirectory(classesDir, classesTemp);
            File[] filesInWebRoot = webRootDir.listFiles();
            for (File file : filesInWebRoot) {
                if ("classes_temp".equals(file.getName())) {
                    continue;
                }
                System.out.println("delete file" + file);
                if (file.isDirectory()) {
                    FileUtils.deleteDirectory(file);
                } else {
                    FileUtils.forceDelete(file);
                }
            }
            FileUtils.copyDirectory(classesTemp, classesDir);
            FileUtils.deleteDirectory(classesTemp);
        } else {
            FileUtils.deleteDirectory(webRootDir);
            FileUtils.deleteDirectory(srcDir);
        }

    }

    private void copyViewFramework() throws IOException {
        File view = new File(PathKit.getRootClassPath() + "/diff/view/" + project.get("viewFramework") + "/"
                             + project.get("viewType"));
        File viewLib = new File(diffLibDir, "template/" + project.get("viewType"));
        FileUtils.copyDirectory(viewLib, libDir);
        FileUtils.copyDirectory(view, webRootDir);

    }

    private void copyDbPool() throws IOException {
        FileUtils.copyDirectory(dbpoolDir, libDir);
    }

    private void copyDbDriver() throws IOException {
        FileUtils.copyDirectory(dbdriverDir, libDir);
    }

    private void copyCommon() throws IOException {
        System.out.println("copy" + commonLibDir + " to " + libDir);
        FileUtils.copyDirectory(commonLibDir, libDir);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public File getSrcDir() {
        return srcDir;
    }

    public void setSrcDir(File srcDir) {
        this.srcDir = srcDir;
    }

    public File getWebRootDir() {
        return webRootDir;
    }

    public void setWebRootDir(File webRootDir) {
        this.webRootDir = webRootDir;
    }

    public File getLibDir() {
        return libDir;
    }

    public void setLibDir(File libDir) {
        this.libDir = libDir;
    }

    public File getCommonLibDir() {
        return commonLibDir;
    }

    public void setCommonLibDir(File commonLibDir) {
        this.commonLibDir = commonLibDir;
    }

    public File getDiffDir() {
        return diffDir;
    }

    public void setDiffDir(File diffDir) {
        this.diffDir = diffDir;
    }

    public File getDiffLibDir() {
        return diffLibDir;
    }

    public void setDiffLibDir(File diffLibDir) {
        this.diffLibDir = diffLibDir;
    }

    public File getDbdriverDir() {
        return dbdriverDir;
    }

    public void setDbdriverDir(File dbdriverDir) {
        this.dbdriverDir = dbdriverDir;
    }

    public File getDbpoolDir() {
        return dbpoolDir;
    }

    public void setDbpoolDir(File dbpoolDir) {
        this.dbpoolDir = dbpoolDir;
    }

    public File getCommonDir() {
        return commonDir;
    }

    public void setCommonDir(File commonDir) {
        this.commonDir = commonDir;
    }

    public File getClassesDir() {
        return classesDir;
    }

    public void setClassesDir(File classesDir) {
        this.classesDir = classesDir;
    }

    public static void main(String[] args) throws Exception {
        Project project = new Project();
        project.set("workspace", "/home/kid/workspace");
        project.set("viewType", "freemarker");
        project.set("jdbcurl", "jdbc:mysql://localhost:3306/code");
        project.set("dbType", new MysqlDialect());
        project.set("driver", "mysql");
        project.set("username", "root");
        project.set("password", "root");
        project.set("packageName", "com.cloud.demo");
        project.set("name", "demo");
        project.set("desc", "在线编码");
        new Generator(project).start();
    }
}
