package com.jfinal.ext.rapid.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import com.jfinal.ext.rapid.exception.GenException;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public class TemplateHelp {

    protected Configuration freeMarkerEngine;
    protected SimpleHash    context;

    public TemplateHelp(String templateDir){
        freeMarkerEngine = new Configuration();
        context = new SimpleHash();
        TemplateLoader loader = null;
        try {
            String file = Thread.currentThread().getContextClassLoader().getResource("").getFile() + templateDir;
            loader = new FileTemplateLoader(new File(file));
        } catch (IOException e) {
            throw new GenException(e);
        }
        freeMarkerEngine.setTemplateLoader(loader);
    }

    public void process(String templetFilename, Writer writer) {
        try {
            Template template = freeMarkerEngine.getTemplate(templetFilename, "UTF-8");
            template.process(context, writer);
            writer.close();
        } catch (TemplateException e) {
            throw new GenException("Problem pasre template file :"+templetFilename,e);
        } catch (IOException e) {
            throw new GenException("not found template file :"+templetFilename,e);
        }
    }

    public void process(String templetFilename, String outputFilename) {
        System.out.println("outputFilename"+outputFilename);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new GenException("Problem writer output  file:"+outputFilename,e);
        } catch (FileNotFoundException e) {
            throw new GenException("not found output file :"+templetFilename,e);
        }
        process(templetFilename, out);
    }

    public void put(String key, Object value) {
        if (value instanceof Map) value = new SimpleHash((Map) value);
        context.put(key, value);
    }

    public void loadTemplateDir(String templateDir) throws IOException {
        freeMarkerEngine.setDirectoryForTemplateLoading(new File(templateDir));
    }
    
    public TemplateHashModel userJavaStaticMethod(String className) {
        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel fileStatics = null;
        try {
            fileStatics = (TemplateHashModel) staticModels.get(className);
        } catch (TemplateModelException e) {
            throw new RuntimeException(e);
        }
        return fileStatics;
    }

}
