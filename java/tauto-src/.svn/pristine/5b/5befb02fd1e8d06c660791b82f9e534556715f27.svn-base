package main.com.weixinHtml.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.vo.ShopUsersVo;

@Repository
public class ShopUsersDaoImpl extends BaseDaoImpl<ShopUsers> implements ShopUsersDao{

	@Override
	public ShopUsers loginOfOpenId(String openId, boolean b) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserCode() throws Exception {
		String userCode = StringCode.getUserCodes(GeneralConstant.userCodeLength);
		List<ShopUsersVo> shopUsersVos = sqlSessionTemplate.selectList(getSqlName("selectByUserCode"), userCode);
		if(shopUsersVos != null && shopUsersVos.size() > 0 ){
			return getUserCode();
		}else{
			return userCode;
		}
	}
}
