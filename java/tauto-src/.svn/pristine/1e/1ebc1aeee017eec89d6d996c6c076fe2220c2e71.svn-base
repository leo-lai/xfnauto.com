package main.com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.search.RoleSearch;
import main.com.system.dao.vo.RoleVo;
import main.com.system.service.RoleService;


@Controller
@RequestMapping("/management/admin")
public class ManagerRoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteById(Integer id) {
		Result result = new Result();
		try{
			Boolean flag = this.roleService.deleteById(id);
			if(flag){
				result.setOK(ResultCode.CODE_STATE_200, "删除成功");
			}else{
				result.setError(ResultCode.CODE_STATE_4006, "删除失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}		
	
	/**
	 * 角色列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/roleList", method = RequestMethod.POST)
	@ResponseBody
	public Result roleList(RoleSearch search) {
		Result result = new Result();
		try{
			result = roleService.roleList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	/**
	 * 角色列表（下拉）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/roleListList", method = RequestMethod.POST)
	@ResponseBody
	public Result roleListList(RoleSearch search) {
		Result result = new Result();
		try{
			result = roleService.roleListList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	/**
	 * 角色添加
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/roleEdit", method = RequestMethod.POST)
	@ResponseBody
	@LogPoint(logDes = "角色管理模块", operateModule = "角色添加或更改")
	public Result roleEdit(RoleSearch search) {
		Result result = new Result();
		try{
			result = roleService.roleEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}		
	/**
	 * 角色删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/roleDelete", method = RequestMethod.POST)
	@ResponseBody
	@LogPoint(logDes = "角色管理模块", operateModule = "角色删除")
	public Result roleDelete(RoleSearch search) {
		Result result = new Result();
		try{
			result = roleService.roleDelete(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}		
	
	/**
	 * 给角色分配菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/setRoleMenu", method = RequestMethod.POST)
	@ResponseBody
	@LogPoint(logDes = "角色管理模块", operateModule = "给角色分配菜单")
	public Result setRoleMenu(RoleSearch search) {
		Result result = new Result();
		try{
			result = roleService.setRoleMenu(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}		
}
