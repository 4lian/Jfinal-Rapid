package com.jfinal.ext.rapid.designer.project;

import java.io.File;
import java.util.Arrays;

import com.jfinal.core.Controller;
import com.jfinal.ext.rapid.kit.AntKit;
import com.jfinal.ext.rapid.kit.Constant;
import com.jfinal.ext.rapid.kit.Generator;
import com.jfinal.ext.rapid.kit.ServerKit;
import com.jfinal.server.IServer;
import com.jfinal.server.ServerFactory;

public class ProjectController extends Controller {

    public void index() {
        setAttr("page", Project.dao.page(getParaToInt(0, 1), 5));
    }

    public void add() {
        setAttr("frameworks", Arrays.asList("bootstrap","dwz","official"));
        setAttr("dbTypes", Arrays.asList("mysql","oracle"));
        setAttr("dbPools", Arrays.asList("c3p0","druid"));
        setAttr("builds", Arrays.asList("ant","maven"));
        setAttr("viewTypes", Arrays.asList("freemarker","beetl","jsp"));
        setAttr("compileLevels", Arrays.asList("1.5","1.6","1.7"));
    }

    public void save() {
        Project project = getModel(Project.class);
        // oracle project.set("ID", UUID.randomUUID().toString());
        project.save();
        forwardAction("/project");
    }

    public void edit() {
        setAttr("project", Project.dao.findById(getPara(0)));
        setAttr("frameworks", Arrays.asList("bootstrap","dwz","official"));
        setAttr("dbTypes", Arrays.asList("mysql","oracle"));
        setAttr("dbPools", Arrays.asList("c3p0","druid"));
        setAttr("builds", Arrays.asList("ant","maven"));
        setAttr("viewTypes", Arrays.asList("freemarker","beetl","jsp"));
        setAttr("compileLevels", Arrays.asList("1.5","1.6","1.7"));
    }

    public void view() {
        setAttr("project", Project.dao.findById(getPara(0)));
    }

    public void update() {
        getModel(Project.class).update();
        forwardAction("/project");
    }

    public void delete() {
        Project.dao.deleteById(getPara(0));
        forwardAction("/project");
    }

    public void createProject() {
        Project project = Project.dao.findById(getParaToInt(0));
        try {
            Generator gener = new Generator(project);
            gener.start();
            System.out.println("代码生成完毕");
            AntKit.javac(gener.getSrcDir().toString(), gener.getClassesDir().toString(), gener.getLibDir().toString());
            String sqlFilePath = project.get("workspace") + "/" + project.get("name") + "/"+project.getName()+".sql";
            AntKit.sqlScript(Constant.Driver.get(project.getDbType()), project.getJdbcurl(), project.getUsername(),
                             project.getPassword(), sqlFilePath);
            forwardAction("/project");
        } catch (Exception e) {
            e.printStackTrace();
            renderJavascript("生成失败：" + e.getMessage());
        }
    }

    public void startProject() {
        Project proj = Project.dao.findById(getPara(0));
        final String webRootDir = proj.get("workspace") + File.separator + proj.get("name") + File.separator
                                  + proj.get("webRoot");
        final int port = Integer.parseInt(proj.getStr("port"));
        final String name = "/" + proj.get("name");
        System.out.println("start>>>webRootDir>>>>" + webRootDir);
        new Thread(new Runnable() {

            @Override
            public void run() {
                IServer server = ServerFactory.getServer(webRootDir, port, name, 0);
                server.start();
            }
        }).start();
        // ServerKit.start(webRootDir, port, name);
        // TODO 重定向到....
        renderText("项目启动");
    }

    public void stopProject() {
        Project proj = Project.dao.findById(getPara(0));
        ServerKit.stop(8080);
        renderText("项目关闭");
    }

    // public static void main(String[] args) {
    // String webRootDir = "/home/zhongkai/workspace/work/demo/WebRoot";
    // //ServerKit.start(webRootDir, 8080, "/demo");
    // IServer server = ServerFactory.getServer(webRootDir, 8080, "/demo", 0);
    // server.start();
    // }
}
