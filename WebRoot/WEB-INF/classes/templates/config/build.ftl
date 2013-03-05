<?xml version="1.0" encoding="UTF-8"?>
    <project name="${project.name}" default="war">
    
    	<property name="src" location="src"/>
    	<property name="resource" location="config" />
    	<property name="webroot" location="WebRoot" />
    	
    	<property name="lib.dir" location="WebRoot/WEB-INF/lib" />
    	<property name="build.dir" location="build" />
    	<property name="class.dir" location="<#noparse>${build.dir}</#noparse>/WEB-INF/classes" />
    	<property name="war" location="<#noparse>${build.dir}</#noparse>/${project.name}.war" />
    
    	<path id="compile.lib">
    		<fileset dir="<#noparse>${lib.dir}</#noparse>">
    			<include name="**/*.jar" />
    		</fileset>
    	</path>
    
    	<target name="clean">
    		<deltree dir="<#noparse>${build.dir}</#noparse>" />
    	</target>
    
    	<target name="prepare" depends="clean">
    		<echo>
                #########################################
                # prepare, create build dirs... #
                #########################################
            </echo>
    		<mkdir dir="<#noparse>${build.dir}</#noparse>" />
    	</target>
    
    
    	<target name="compile" depends="prepare">
    		<echo>
        	        #######################################
        	        # compile ..                          #
        	        #######################################
        	</echo>
        	<mkdir dir="<#noparse>${class.dir}</#noparse>" />
    		<javac encoding="UTF-8" source="1.6" target="1.6" srcdir="<#noparse>${src}</#noparse>" destdir="<#noparse>${class.dir}</#noparse>" debug="on">
    			<compilerarg line="-encoding UTF-8" />
    			<classpath>
    				<path refid="compile.lib" />
    			</classpath>
    		</javac>
    	</target>
    
    
    	<target name="war" depends="compile">
    		<echo>
        	        #######################################
        	        #                 war                 #
        	        #######################################
        	</echo>
    		<copy todir="<#noparse>${build.dir}</#noparse>">
    			<fileset dir="<#noparse>${webroot}</#noparse>">
    				<exclude name="**/WEB-INF/classes/**" />
    				<include name="**/*" />
    			</fileset>
    		</copy>
    		<copy todir="<#noparse>${class.dir}</#noparse>">
    			<fileset dir="<#noparse>${resource}</#noparse>">
    				<include name="**/*" />
    			</fileset>
    		</copy>
    		<war destfile="<#noparse>${war}</#noparse>" webxml="<#noparse>${build.dir}</#noparse>/WEB-INF/web.xml">
    			<fileset dir="<#noparse>${build.dir}</#noparse>" />
    		</war>
    	</target>
    
    
    </project>

