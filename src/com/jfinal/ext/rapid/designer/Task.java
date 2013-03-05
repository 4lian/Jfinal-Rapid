package com.jfinal.ext.rapid.designer;

import java.util.ArrayList;
import java.util.List;

public class Task {

    /**
     * 任务名称
     */
    private String taskname;
    /**
     * 生成文件名 （beanshell脚本）
     */
    private String filename;
    /**
     * 模版文件路径（beanshell脚本）
     */
    private String templatepath;
    /**
     * 目标文件输出路径类型
     */
    private String outtype;
    /**
     * 文件夹路径（beanshell脚本）
     */
    private String floder;
    
    private String type;
    
    private  List<Param> params = new ArrayList();
    
    public void addParam(Param param){
    	params.add(param);
    }
    public String getTaskname() {
        return taskname;
    }
    
    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getTemplatepath() {
        return templatepath;
    }
    
    public void setTemplatepath(String templatepath) {
        this.templatepath = templatepath;
    }
    
    public String getOuttype() {
        return outtype;
    }
    
    public void setOuttype(String outtype) {
        this.outtype = outtype;
    }
    
    public String getFloder() {
        return floder;
    }
    
    public void setFloder(String floder) {
        this.floder = floder;
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "Task [taskname=" + taskname + ", filename=" + filename
				+ ", templatepath=" + templatepath + ", outtype=" + outtype
				+ ", floder=" + floder + ", type=" + type + "]";
	}
	public List<Param> getParams() {
		return params;
	}
	public void setParams(List<Param> params) {
		this.params = params;
	}
    
}
