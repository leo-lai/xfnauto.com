package main.com.car.dao.vo;

import main.com.car.dao.po.CarCarparameter;

public class CarCarparameterVo extends CarCarparameter{

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

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
