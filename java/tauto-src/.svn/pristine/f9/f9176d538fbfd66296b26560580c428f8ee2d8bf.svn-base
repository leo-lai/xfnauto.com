package main.com.system.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.Menu;
import main.com.system.dao.search.MenuSearch;
import main.com.system.dao.vo.MenuVo;
import main.com.system.service.ManagerMenuService;

@Controller
@RequestMapping("/management/admin")
public class ManagerMenuController extends BaseController{
	
	public static Logger logger = Logger.getLogger(ManagerMenuController.class);

	@Autowired
	ManagerMenuService managerMenuService;
	
	/**
	 * 获取菜单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuList")
	public Result gridTree(MenuSearch menuSearch) {
		Result result = new Result();
		if(menuSearch.getMenuId() == null){
			menuSearch.setParentId(0);
		}else{
			menuSearch.setParentId(menuSearch.getMenuId());
			menuSearch.setMenuId(null);
		}
		try {
			result = managerMenuService.menuList(menuSearch,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 获取菜单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuListTree")
	public Result menuListTree(MenuSearch menuSearch) {
		Result result = new Result();
		try {
			result = managerMenuService.menuListTree(menuSearch,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	@RequestMapping(value = "/allMenuList", method = RequestMethod.POST)
	@ResponseBody
	public Result show(MenuSearch menuSearch) {
		Result result = new Result();
		try {
			result = this.managerMenuService.getAllMenu(menuSearch,result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/addMenu")
	@ResponseBody
	@LogPoint(logDes = "后台菜单模块", operateModule = "添加菜单")
	public Result addMenu(MenuSearch menuSearch) {
		Result result = new Result();
		try{
			result = managerMenuService.addMenu(menuSearch,result);		
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/editMenu")
	@ResponseBody
	@LogPoint(logDes = "后台菜单模块", operateModule = "修改菜单")
	public Result editMenu(MenuSearch menuSearch) {
		Result result = new Result();
		try{
			result = managerMenuService.editMenu(menuSearch,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "保存数据错误，请联系IT部");
		}
		return result;
	}
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	@LogPoint(logDes = "后台菜单模块", operateModule = "删除菜单")
	public Result deleteMenu(MenuSearch menuSearch) {
		Result result = new Result();
		try{
			result = managerMenuService.deleteMenu(menuSearch,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
}
