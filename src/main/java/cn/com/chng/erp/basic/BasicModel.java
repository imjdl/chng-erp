package cn.com.chng.erp.basic;

import cn.com.chng.erp.annotations.ParameterName;
import cn.com.chng.erp.utils.ApplicationHandler;
import org.apache.commons.lang.Validate;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasicModel {
    protected List<String> obtainAllFieldNames() {
        Class<?> modelClass = this.getClass();
        Field[] fields = modelClass.getDeclaredFields();
        List<String> allFieldNames = new ArrayList<String>();
        for (Field field : fields) {
            allFieldNames.add(field.getName());
        }
        return allFieldNames;
    }

    protected String obtainParameterName(String fieldName) throws NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(fieldName);
        String parameterName = null;
        if (field != null) {
            ParameterName parameterNameAnnotation = field.getDeclaredAnnotation(ParameterName.class);
            if (parameterNameAnnotation != null) {
                parameterName = parameterNameAnnotation.value();
            } else {
                parameterName = fieldName;
            }
        }
        return parameterName;
    }

    public boolean validate() {
        Validator validator = ApplicationHandler.obtainValidator();
        boolean isValidateOk = true;
        if (validator != null) {
            List<String> allFieldNames = obtainAllFieldNames();
            for (String fieldName : allFieldNames) {
                Iterator<ConstraintViolation<BasicModel>> iterator = validator.validateProperty(this, fieldName).iterator();
                if (iterator.hasNext()) {
                    isValidateOk = false;
                    break;
                }
            }
        }
        return isValidateOk;
    }

    public String obtainParameterErrorMessage(String fieldName) throws NoSuchFieldException {
        return ApplicationHandler.obtainParameterErrorMessage(obtainParameterName(fieldName));
    }

    public void validateAndThrow() throws NoSuchFieldException {
        Validator validator = ApplicationHandler.obtainValidator();
        if (validator != null) {
            List<String> allFieldNames = obtainAllFieldNames();
            for (String fieldName : allFieldNames) {
                Iterator<ConstraintViolation<BasicModel>> iterator = validator.validateProperty(this, fieldName).iterator();
                if (iterator.hasNext()) {
                    Validate.isTrue(false, obtainParameterErrorMessage(fieldName));
                }
            }
        }
    }
}
