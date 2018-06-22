package main.com.stock.dao.vo;

import java.util.List;

import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;

public class StockStorageVo extends StockStorage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<StockCarVo> stockCarsVo;
	
	private Integer number;

	public List<StockCarVo> getStockCarsVo() {
		return stockCarsVo;
	}


	public void setStockCarsVo(List<StockCarVo> stockCarsVo) {
		this.stockCarsVo = stockCarsVo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}
}
