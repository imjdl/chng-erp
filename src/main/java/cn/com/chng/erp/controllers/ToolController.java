package cn.com.chng.erp.controllers;

import cn.com.chng.erp.basic.BasicController;
import cn.com.chng.erp.basic.BasicDomain;
import cn.com.chng.erp.utils.ApplicationHandler;
import cn.com.chng.erp.utils.LogUtils;
import cn.com.chng.erp.utils.NamingStrategyUtils;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/tool")
public class ToolController extends BasicController {
    @RequestMapping(value = "/generateInsertSql")
    @ResponseBody
    public String generateInsertSql() {
        String returnValue = null;
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        try {
            String domainClassName = requestParameters.get("domainClassName");
            Validate.notNull(domainClassName, "参数(domainClassName)不能为空！");

            Class<?> domainClass = Class.forName(domainClassName);
            Field[] fields = domainClass.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            for (Field field : fields) {
                String fieldName = field.getName();
                if ("id".equals(fieldName) || "createTime".equals(fieldName) || "lastUpdateTime".equals(fieldName) || "deleted".equals(fieldName)) {
                    continue;
                }
                fieldList.add(field);
            }

            if (domainClass.getSuperclass() == BasicDomain.class) {
                Field[] superclassFields = BasicDomain.class.getDeclaredFields();
                for (Field field : superclassFields) {
                    String fieldName = field.getName();
                    if ("id".equals(fieldName) || "createTime".equals(fieldName) || "lastUpdateTime".equals(fieldName) || "deleted".equals(fieldName)) {
                        continue;
                    }
                    fieldList.add(field);
                }
            }

            StringBuffer insertSql = new StringBuffer("INSERT INTO ");
            String domainClassSimpleName = domainClass.getSimpleName();
            String tableName = NamingStrategyUtils.camelCaseToUnderscore(domainClassSimpleName.substring(0, 1).toLowerCase() + domainClassSimpleName.substring(1));
            insertSql.append(tableName);
            insertSql.append("(");
            int size = fieldList.size();
            int count = 0;
            for (int index = 0; index < size; index++) {
                String fieldName = fieldList.get(index).getName();
                count = count + 1;
                insertSql.append(NamingStrategyUtils.camelCaseToUnderscore(fieldName)).append(", ");
                if (count % 3 == 0 && count != size) {
                    insertSql.append("<br>");
                }
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            insertSql.deleteCharAt(insertSql.length() - 1);
            insertSql.append(")");
            insertSql.append("<br>");
            insertSql.append("VALUES(");

            count = 0;
            for (int index = 0; index < size; index++) {
                String fieldName = fieldList.get(index).getName();
                count = count + 1;
                insertSql.append("#{").append(fieldName).append("}, ");
                if (count % 3 == 0 && count != size) {
                    insertSql.append("<br>");
                }
            }
            insertSql.deleteCharAt(insertSql.length() - 1);
            insertSql.deleteCharAt(insertSql.length() - 1);
            insertSql.append(")");

            returnValue = insertSql.toString();
        } catch (Exception e) {
            LogUtils.error("生成插入SQL失败", controllerSimpleName, "generateInsertSql", e);
            returnValue = e.getMessage();
        }
        return returnValue;
    }

    @RequestMapping(value = "/generateUpdateSql")
    @ResponseBody
    public String generateUpdateSql() {
        String returnValue = null;
        Map<String, String> requestParameters = ApplicationHandler.getRequestParameters();
        try {
            String domainClassName = requestParameters.get("domainClassName");
            Validate.notNull(domainClassName, "参数(domainClassName)不能为空！");

            Class<?> domainClass = Class.forName(domainClassName);
            Field[] fields = domainClass.getDeclaredFields();

            List<Field> fieldList = new ArrayList<Field>();
            for (Field field : fields) {
                fieldList.add(field);
            }

            if (domainClass.getSuperclass() == BasicDomain.class) {
                Field[] superclassFields = BasicDomain.class.getDeclaredFields();
                for (Field field : superclassFields) {
                    fieldList.add(field);
                }
            }

            StringBuffer updateSql = new StringBuffer("UPDATE ");
            String domainClassSimpleName = domainClass.getSimpleName();
            String tableName = NamingStrategyUtils.camelCaseToUnderscore(domainClassSimpleName.substring(0, 1).toLowerCase() + domainClassSimpleName.substring(1));
            updateSql.append(tableName);
            updateSql.append("<br>SET ");

            for (Field field : fieldList) {
                String fieldName = field.getName();
                if ("id".equals(fieldName) || "createTime".equals(fieldName) || "lastUpdateTime".equals(fieldName)) {
                    continue;
                }
                updateSql.append(NamingStrategyUtils.camelCaseToUnderscore(fieldName));
                updateSql.append(" = ");
                updateSql.append("#{").append(fieldName).append("},<br>");
            }
            updateSql.deleteCharAt(updateSql.length() - 1);
            updateSql.deleteCharAt(updateSql.length() - 1);
            updateSql.deleteCharAt(updateSql.length() - 1);
            updateSql.deleteCharAt(updateSql.length() - 1);
            updateSql.deleteCharAt(updateSql.length() - 1);
            updateSql.append("<br>WHERE id = #{id}");
            returnValue = updateSql.toString();
        } catch (Exception e) {
            LogUtils.error("生成更新SQL失败", controllerSimpleName, "generateInsertSql", e);
            returnValue = e.getMessage();
        }
        return returnValue;
    }
}
