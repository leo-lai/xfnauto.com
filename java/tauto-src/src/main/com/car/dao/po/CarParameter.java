package main.com.car.dao.po;

import java.io.Serializable;

public class CarParameter implements Serializable {
    /**
     * 参数ID
     */
    private Integer parameterId;

    /**
     * 参数名称
     */
    private String parameterName;

    /**
     * 类别代码
     */
    private String typeCode;

    /**
     * 所属类别
     */
    private String typeName;

    /**
     * 参数ID
     * @return parameter_id 参数ID
     */
    public Integer getParameterId() {
        return parameterId;
    }

    /**
     * 参数ID
     * @param parameterId 参数ID
     */
    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    /**
     * 参数名称
     * @return parameter_name 参数名称
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * 参数名称
     * @param parameterName 参数名称
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * 类别代码
     * @return type_code 类别代码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 类别代码
     * @param typeCode 类别代码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 所属类别
     * @return type_name 所属类别
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 所属类别
     * @param typeName 所属类别
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}