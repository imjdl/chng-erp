package cn.com.chng.erp.constants;

import java.nio.charset.Charset;

/**
 * Created by liuyandong on 2017/7/24.
 */
public class Constants {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";

    /**
     * 字符集相关常量
     */
    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final Charset CHARSET_GBK = Charset.forName("GBK");
    public static final String CHARSET_NAME_UTF_8 = "UTF-8";
    public static final String CHARSET_NAME_GBK = "GBK";

    public static final String FALSE = "false";
    public static final String TRUE = "true";

    /**
     * 配置相关常量
     */
    public static final String PRODUCTION_PROPERTIES = "production.properties";
    public static final String DEPLOYMENT_ENVIRONMENT = "deployment.environment";
    public static final String PARTITION_CODE = "partition.code";
    public static final String SERVICE_NAME = "service.name";

    public static final String SQL_OPERATION_SYMBOL_IN = "IN";
    public static final String SQL_OPERATION_SYMBOL_LIKE = "LIKE";
    public static final String SQL_OPERATION_SYMBOL_EQUALS = "=";
    public static final String SQL_OPERATION_SYMBOL_LESS_THAN = "<";
    public static final String SQL_OPERATION_SYMBOL_LESS_THAN_EQUALS = "<=";
    public static final String SQL_OPERATION_SYMBOL_GREATER_THAN = ">";
    public static final String SQL_OPERATION_SYMBOL_GREATER_THAN_EQUALS = ">=";

    public static final String PARAMETER_ERROR_MESSAGE_PATTERN = "参数(%s)错误！";
    public static final String NETWORK_ERROR_MESSAGE = "网络错误！";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String KEY_PLATFORM_PRIVATE_KEY = "_platform_private_key";

    public static final String SERVICE_NAME_ERP = "erp";

    public static final Integer SYSTEM_PRIVILEGE_TYPE_PERMIT_ALL = 1;
    public static final Integer SYSTEM_PRIVILEGE_TYPE_HAS_AUTHORITY = 2;
    public static final Integer SYSTEM_PRIVILEGE_TYPE_AUTHENTICATED = 3;
    public static final String AUTHENTICATED = "authenticated";
    public static final String PERMIT_ALL = "permitAll";
    public static final String HAS_AUTHORITY_FORMAT = "hasAuthority('%s')";
}
