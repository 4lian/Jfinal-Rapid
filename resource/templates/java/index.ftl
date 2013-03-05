package ${project.packageName};
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
import com.jfinal.core.JFinal;

public class IndexController extends Controller {
    protected final Logger logger = Logger.getLogger(getClass());

	public  void index(){
		logger.debug("debug");
		<#if project.viewType == "jsp">
		render("/index.jsp");
		<#else>
		render("/index.html");
		</#if>
	}
	
	public void startProject(){
		JFinal.start("${project.webRoot}", ${project.port}, "/${project.name}", 5);
	}
	
	public void stopProject(){
		JFinal.stop();
	}
	
}
