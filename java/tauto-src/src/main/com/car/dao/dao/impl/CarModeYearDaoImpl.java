package main.com.car.dao.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import main.com.car.dao.dao.CarModeYearDao;
import main.com.car.dao.po.CarModeYear;
import main.com.frame.dao.BaseDao;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.system.dao.dao.DictionaryDao;
import main.com.system.dao.po.Dictionary;

@Repository
public class CarModeYearDaoImpl extends BaseDaoImpl<CarModeYear> implements CarModeYearDao{
}
