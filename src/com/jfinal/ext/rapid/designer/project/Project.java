package com.jfinal.ext.rapid.designer.project;

import java.util.List;

import com.jfinal.ext.rapid.designer.entity.Entity;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Project extends Model<Project> {

    public static final Project dao = new Project();
    
    public List<Project> findAll() {
        return find("select * from project");
    }

    public List<Entity> getEntities(){
        return Entity.dao.find("select * from entity where project_id = ?",get("id"));
    }

    public Page<Project> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from project order by id asc");
    }

    public boolean checkEmbedded() {
        return getInt("embedded") == 1 ? true : false;
    }

    public String getTemplates() {
        return get("templates");
    }

    public String getWebRoot() {
        return get("webRoot");
    }

    public String getSrc() {
        return get("src");
    }

    public String getDbType() {
        return get("dbType");
    }

    public String getJdbcurl() {
        return get("jdbcurl");
    }

    public String getUsername() {
        return get("username");
    }

    public String getPassword() {
        return get("password");
    }

    public String getPackageName() {
        return get("packageName");
    }

    public String getViewType() {
        return get("viewType");
    }

    public String getName() {
        return get("name");
    }

    public String getDbPool() {
        return get("dbPool");
    }

    public String getIp() {
        return get("ip");
    }

    public String getDesc() {
        return get("desc");
    }

    public String getPort() {
        return get("port");
    }

    public String getViewFramework() {
        return get("viewFramework");
    }

    public String getWorkspace() {
        return get("workspace");
    }

    public String getConfig() {
        return get("config");
    }

    public String getCompileLeve() {
        return get("compileLeve");
    }

    public String getDriver() {
        return get("driver");
    }


}
