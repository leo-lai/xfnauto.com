package main.com.Test;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;  
import org.mybatis.generator.api.IntrospectedTable;  
import org.mybatis.generator.api.dom.java.*;  
import org.mybatis.generator.api.dom.xml.XmlElement;  
import org.mybatis.generator.config.MergeConstants;  
import org.mybatis.generator.config.PropertyRegistry;  
import org.mybatis.generator.internal.util.StringUtility;  

/**
 * 重写 Generator 
 * 使得生成的文件可以自动带上数据库的中文注释
 * @author Zwen
 *
 */
public class MyCommentGenerator implements CommentGenerator {

    private Properties properties;  
    private Properties systemPro;  
    private boolean suppressDate;  
    private boolean suppressAllComments;  
    private String currentDateStr;  
  
    public MyCommentGenerator() {  
        super();  
        properties = new Properties();  
        systemPro = System.getProperties();  
        suppressDate = false;  
        suppressAllComments = false;  
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());  
    }  
  
    public void addJavaFileComment(CompilationUnit compilationUnit) {  
        return;  
    }  
  

    /**
     * 添加XML文件进来
     */
    public void addComment(XmlElement xmlElement) {  
        return;  
    }  
  
    public void addRootComment(XmlElement rootElement) {  
        return;  
    }  
  
    public void addConfigurationProperties(Properties properties) {  
        this.properties.putAll(properties);  
        suppressDate = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));  
        suppressAllComments = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));  
    }  
  

    /**
     * 拼接注释文本
     * @param javaElement
     * @param markAsDoNotDelete
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {  
        javaElement.addJavaDocLine(" *");  
        StringBuilder sb = new StringBuilder();  
        sb.append(" * ");  
        sb.append(MergeConstants.NEW_ELEMENT_TAG);  
        if (markAsDoNotDelete) {  
            sb.append(" do_not_delete_during_merge");  
        }  
        String s = getDateString();  
        if (s != null) {  
            sb.append(' ');  
            sb.append(s);  
        }  
        javaElement.addJavaDocLine(sb.toString());  
    }  
  
    protected String getDateString() {  
        String result = null;  
        if (!suppressDate) {  
            result = currentDateStr;  
        }  
        return result;  
    }  
  
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {  
        if (suppressAllComments) {  
            return;  
        }  
        StringBuilder sb = new StringBuilder();  
        innerClass.addJavaDocLine("/**");  
        sb.append(" * ");  
        sb.append(introspectedTable.getFullyQualifiedTable());  
        sb.append(" ");  
        sb.append(getDateString());  
        innerClass.addJavaDocLine(sb.toString());  
        innerClass.addJavaDocLine(" */");  
    }  
  
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {  
        if (suppressAllComments) {  
            return;  
        }  
  
        StringBuilder sb = new StringBuilder();  
  
        innerEnum.addJavaDocLine("/**");  
        sb.append(" * ");  
        sb.append(introspectedTable.getFullyQualifiedTable());  
        innerEnum.addJavaDocLine(sb.toString());  
        innerEnum.addJavaDocLine(" */");  
    }  
  
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,  
                                IntrospectedColumn introspectedColumn) {  
        if (suppressAllComments) {  
            return;  
        }  
        StringBuilder sb = new StringBuilder();  
        field.addJavaDocLine("/**");  
        sb.append(" * ");  
        sb.append(introspectedColumn.getRemarks());  
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");  
    }  
  
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {  
        if (suppressAllComments) {  
            return;  
        }  
        StringBuilder builder = new StringBuilder();
        field.addJavaDocLine("/**");  
        builder.append(" * ");  
        builder.append(introspectedTable.getFullyQualifiedTable());  
        field.addJavaDocLine(builder.toString());  
        field.addJavaDocLine(" */"); 
    }  
  
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {}  
  
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {  
        if (suppressAllComments) {  
            return;  
        }
    }  
  
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,  
                                 IntrospectedColumn introspectedColumn) {  
        if (suppressAllComments) {  
            return;  
        }  
        method.addJavaDocLine("/**");  
        StringBuilder sb = new StringBuilder();  
        sb.append(" * ");  
        sb.append(introspectedColumn.getRemarks());  
        method.addJavaDocLine(sb.toString());
        sb.setLength(0);  
        sb.append(" * @return ");  
        sb.append(introspectedColumn.getActualColumnName());  
        sb.append(" ");  
        sb.append(introspectedColumn.getRemarks());  
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");  
    }  
  
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,  
                                 IntrospectedColumn introspectedColumn) {  
        if (suppressAllComments) {  
            return;  
        }
        method.addJavaDocLine("/**");  
        StringBuilder sb = new StringBuilder();  
        sb.append(" * ");  
        sb.append(introspectedColumn.getRemarks());  
        method.addJavaDocLine(sb.toString());
        Parameter parm = method.getParameters().get(0);  
        sb.setLength(0);  
        sb.append(" * @param ");  
        sb.append(parm.getName());  
        sb.append(" ");  
        sb.append(introspectedColumn.getRemarks());  
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");  
    }  
  
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {  
        if (suppressAllComments) {  
            return;  
        }  
        StringBuilder builder = new StringBuilder();
        innerClass.addJavaDocLine("/**");  
        builder.append(" * ");  
        builder.append(introspectedTable.getFullyQualifiedTable());  
        innerClass.addJavaDocLine(builder.toString());  
  
        builder.setLength(0);  
        builder.append(" * @author "); 
        builder.append(systemPro.getProperty("user.name"));  
        builder.append(" ");  
        builder.append(currentDateStr); 
        innerClass.addJavaDocLine(" */");  
    }  
	
}
