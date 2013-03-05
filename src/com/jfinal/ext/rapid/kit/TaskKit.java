package com.jfinal.ext.rapid.kit;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import bsh.EvalError;

import com.jfinal.ext.rapid.designer.Param;
import com.jfinal.ext.rapid.designer.Task;
import com.jfinal.ext.rapid.designer.entity.Entity;
import com.jfinal.ext.rapid.designer.project.Project;
import com.jfinal.ext.rapid.kit.script.BeanShellFactory;
import com.jfinal.ext.rapid.kit.script.BeanShellWrapper;
import com.jfinal.ext.rapid.template.TemplateHelp;
import com.jfinal.kit.StringKit;

/**
 * 生成任务的静态工具方法
 * 
 * @author kid
 * 
 */
public class TaskKit {

	private static TemplateHelp templateHelp;
	
	public static List<Task> createTaskls() {
		List<Task> tasks = new ArrayList<Task>();
		File file = new File(TaskKit.class.getClassLoader().getResource("")
				.getFile());
		File[] files = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().equals("tasks.xml")) {
					return true;
				}
				return false;
			}
		});
		for (File xmlfile : files) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				try {
					Document doc = (Document) db.parse(xmlfile);
					Element root = (Element) doc.getDocumentElement();
					NodeList nodelists = root.getElementsByTagName("task");
					for (int i = 0; i < nodelists.getLength(); i++) {
						Element elementTask = (Element) nodelists.item(i);
						Task task = new Task();
						task.setTaskname(elementTask.getAttribute("name"));
						NodeList filename = elementTask
								.getElementsByTagName("filename");
						if (filename.getLength() == 1) {
							Element e = (Element) filename.item(0);
							Text t = (Text) e.getFirstChild();
							task.setFilename(t.getNodeValue());
						}
						NodeList templatepath = elementTask
								.getElementsByTagName("templatepath");
						if (templatepath.getLength() == 1) {
							Element e = (Element) templatepath.item(0);
							Text t = (Text) e.getFirstChild();
							task.setTemplatepath(t.getNodeValue());
						}
						NodeList outtype = elementTask
								.getElementsByTagName("outtype");
						if (outtype.getLength() == 1) {
							Element e = (Element) outtype.item(0);
							Text t = (Text) e.getFirstChild();
							task.setOuttype(t.getNodeValue());
						}
						NodeList floder = elementTask
								.getElementsByTagName("floder");
						if (floder.getLength() == 1) {
							Element e = (Element) floder.item(0);
							Text t = (Text) e.getFirstChild();
							task.setFloder(t.getNodeValue());
						}
						NodeList type = elementTask
								.getElementsByTagName("type");
						if (type.getLength() == 1) {
							Element e = (Element) type.item(0);
							Text t = (Text) e.getFirstChild();
							task.setType(t.getNodeValue());
						}
						NodeList params = elementTask
								.getElementsByTagName("params");
						if (params.getLength() == 1) {
							Element e = (Element) params.item(0);
							NodeList name = e.getElementsByTagName("param");
							for (int j = 0; j < name.getLength(); j++) {
								Element elementParam = (Element) name.item(j);
								Param param = new Param();
								if (name.getLength()!= 0) {
									Element e1 = (Element) name.item(j);
									Text t1 = (Text) e1.getFirstChild();
									param.setName((elementParam
											.getAttribute("name")));
									param.setValue(t1.getNodeValue());
									task.addParam(param);
								}
							}
						}
						tasks.add(task);
					}

				} catch (SAXException | IOException e) {
					e.printStackTrace();
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return tasks;
	}

	public static void processTask(Project project, List<Entity> entities,
			Task task) throws EvalError {

		BeanShellWrapper bs = BeanShellFactory.createBeanShell();
		initBeanShell(project, entities, task, bs);
		initTemplateHelp(project, entities);
		if ("single".equalsIgnoreCase(task.getType())) {
			processTemplate(bs, task);

		} else if ("multi".equalsIgnoreCase(task.getType())) {
			for (Entity entity : entities) {
				bs.put("entity", entity);
				templateHelp.put("entity", entity);
				processTemplate(bs, task);
			}
		}
	}

	private static void processTemplate(BeanShellWrapper bs, Task task) {
		
		File floderDir;
		try {
			floderDir = new File(bs.eval(task.getFloder()) + "");
			if (!floderDir.exists()) {
				floderDir.mkdirs();
			}
			List<Param> params = task.getParams();
			for (Param param : params) {
				if (param.getName() != "" ) {
					String value = param.getValue();
					System.out.println("value:"+value);
					templateHelp.put(param.getName(), bs.eval(value));
				}
			}
			String templetFilename = (String) bs.eval(task.getTemplatepath());
			String floder = (String) bs.eval(task.getFloder());
			String filename = (String) bs.eval(task.getFilename());
			String outputFilename = floder + filename;
			templateHelp.process(templetFilename, outputFilename);
		} catch (EvalError e) {
			e.printStackTrace();
		}

	}

	private static void initTemplateHelp(Project project, List<Entity> entities) {
		
		templateHelp = new TemplateHelp(project.getTemplates());
		templateHelp.put("project", project);
		templateHelp.put("entities", entities);
	}

	private static void initBeanShell(Project project, List<Entity> tables,
			Task task, BeanShellWrapper bs) throws EvalError {
		
		bs.put("project", project);
		bs.put("tables", tables);
		bs.put("outtype", task.getOuttype());
		bs.put("TaskKit", new TaskKit());
		bs.put("Constant.ViewType", new Constant.ViewType());
		bs.put("Constant.Driver", new Constant.Driver());
	}

    public static String getDestdir(Project project, String outtype) {
        
        String basePath = project.getWorkspace() + File.separator
                + project.getName() + File.separator;
        if (outtype.equals("OUT_TYPE_SRC")) {
            basePath += project.getSrc();
        } else if (outtype.equals("OUT_TYPE_CONFIG")) {
            basePath += project.getConfig();
        } else if (outtype.equals("OUT_TYPE_WEB")) {
            basePath += project.getWebRoot();
        } else if (outtype.equals("OUT_TYPE_ROOT")) {
        }
        return basePath;
    }

	public static String toCapitalize(String str) {
		
		return StringKit.firstCharToUpperCase(str);
	}

	public static List<String> createLib(Project project) {

		List<String> libs = new ArrayList<String>();
		File webRootDir = new File(project.getWorkspace() + File.separator
				+ project.getName() + File.separator + project.getWebRoot());
		File libDir = new File(webRootDir, "WEB-INF/lib");
		File[] files = libDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				String filename = files[i].getName();
				libs.add(filename);
			}
		}
		templateHelp.put("_libs", libs);
		return libs;
	}
}
