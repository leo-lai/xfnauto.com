package main.com.weixinHtml.service.impl;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.fit.cssbox.demo.ImageRenderer;
import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.FileInterchangeBytes;
import main.com.utils.GeneralConstant;
import main.com.utils.QiniuUtils;
import main.com.utils.StringUtil;
import main.com.utils.SystemPath;
import main.com.weixinHtml.dao.dao.ShareMaterialDao;
import main.com.weixinHtml.dao.dao.ShareMaterialInfoDao;
import main.com.weixinHtml.dao.po.ShareMaterial;
import main.com.weixinHtml.dao.po.ShareMaterialInfo;
import main.com.weixinHtml.dao.search.ShareMaterialInfoSearch;
import main.com.weixinHtml.dao.search.ShareMaterialSearch;
import main.com.weixinHtml.dao.vo.ShareMaterialInfoVo;
import main.com.weixinHtml.dao.vo.ShareMaterialVo;
import main.com.weixinHtml.service.ShareMaterialInfoService;

@Service
public class ShareMaterialInfoServiceImpl extends BaseServiceImpl<ShareMaterialInfo> implements ShareMaterialInfoService{

	@Autowired
	ShareMaterialInfoDao shareMaterialInfoDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	ShareMaterialDao shareMaterialDao;
	
	@Override
	protected BaseDao<ShareMaterialInfo> getBaseDao() {
		return shareMaterialInfoDao;
	}

	@Override
	public Result myShareMaterialInfoList(ShareMaterialInfoSearch search, Result result,SystemUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());		
		params.put("systemUserId", users.getUsersId());
		List<ShareMaterialInfoVo> infoVos = shareMaterialInfoDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", infoVos);
		return new Result(returnMap);
	}

	@Override
	public Result shareMaterialInfoEdit(ShareMaterialInfoSearch search, Result result, SystemUsers users) throws Exception {
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
//		if(organization.getOrgLevel() < GeneralConstant.Org.Level_3) {
//			result.setError("你不是门店级别用户，无法进行此操作");
//			return result;
//		}
		ShareMaterialVo materialVo = shareMaterialDao.selectById(search.getMaterialId());
		if(materialVo == null || materialVo.getOverDelete()) {
			result.setError("你选择的公共素材不存在或者已删除");
			return result;
		}
		ShareMaterialInfoVo materialInfoVo = null;
		if(StringUtil.isEmpty(search.getMaterialInfoId())) {
			materialInfoVo = new ShareMaterialInfoVo();
			materialInfoVo.setCreateDate(new Date());
			materialInfoVo.setSystemUserId(users.getUsersId());
			materialInfoVo.setOverDelete(false);
			materialInfoVo.setOverShare(false);
			materialInfoVo.setMaterialId(materialVo.getMaterialId());
		}else {
			materialInfoVo = shareMaterialInfoDao.selectById(search.getMaterialInfoId());
			if(materialInfoVo == null) {
				result.setError("你选择的素材不存在或者已删除");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getMaterialInfoName())) {
			materialInfoVo.setMaterialInfoName(search.getMaterialInfoName());
		}
		if(StringUtil.isEmpty(search.getImage())) {
			result.setError("请上传素材图片");
			return result;
		}
		materialInfoVo.setImage(search.getImage());
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			materialInfoVo.setRemarks(search.getRemarks());
		}
		return save(materialInfoVo, result, materialInfoVo.getMaterialInfoId());
	}

	@Override
	public Result shareMaterialInfoDel(ShareMaterialInfoSearch search, Result result, SystemUsers users)
			throws Exception {
		ShareMaterialInfoVo materialInfoVo = shareMaterialInfoDao.selectById(search.getMaterialInfoId());
		if(materialInfoVo == null) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		if(organization.getOrgLevel() < GeneralConstant.Org.Level_3) {
			result.setError("你的权限不足");
			return result;
		}
		if(materialInfoVo.getOverDelete()) {
			result.setError("该素材已删除，无需重复操作");
			return result;
		}
		materialInfoVo.setOverDelete(true);
		return save(materialInfoVo, result, materialInfoVo.getMaterialInfoId());
	}

	
	public Result shareMaterialInfoShar_back(ShareMaterialInfoSearch search, Result result, SystemUsers users,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		ShareMaterialInfoVo materialInfoVo = shareMaterialInfoDao.selectByIdJoin(search.getMaterialInfoId());
		if(materialInfoVo == null) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		ShareMaterial shareMaterial = materialInfoVo.getMaterialVo();
		if(shareMaterial == null) {
			result.setError("该素材的上级素材不存在或者已删除，分享失败");
			return result;
		}
		String url = "http://localhost:8080/tauto/emInterface/employee/shop/shareMaterialInfoSharHtml?sessionId="+users.getSessionId()+"&materialInfoId="+
				materialInfoVo.getMaterialInfoId();
		String path = SystemPath.ROOT_PATH+File.separator+"res"+File.separator+"image"+File.separator+users.getUsersId()+".png";
		File file = new File(path);
		if(!file.exists()){
		    //先得到文件的上级目录，并创建上级目录，在创建文件
		    file.getParentFile().mkdir();
		    try {
		        //创建文件
		        file.createNewFile();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		  JFrame window = new JFrame();  
	        HtmlPanel panel = new HtmlPanel();  
	        window.getContentPane().add(panel);  
	        window.setSize(600, 400);  
	        window.setVisible(true);  
	        new SimpleHtmlRendererContext(panel, new SimpleUserAgentContext())  
	                .navigate(url);  
	        System.out.println("10");  
	        Thread.sleep(10000);
	        BufferedImage image = new BufferedImage(panel.getWidth(),  
	                panel.getHeight(), BufferedImage.TYPE_INT_ARGB);  
	  
	        // paint the editor onto the image  
	        SwingUtilities.paintComponent(image.createGraphics(), panel,  
	                new JPanel(), 0, 0, image.getWidth(), image.getHeight());  
	        // save the image to file  
	        ImageIO.write((RenderedImage) image, "png", new File(path));  
	        System.out.println("www");  
		
		String pathBack = QiniuUtils.uploadFileBackPath(FileInterchangeBytes.getBytes(path), System.currentTimeMillis()+"", null);//默认未images空间
		 if(StringUtil.isEmpty(pathBack)){
			 System.out.println("上传文件出错");
			 result.setError(ResultCode.CODE_STATE_4009, "生成分享截图失败");
		 }else{
			 //删除旧文件
			 file.delete();
			 result.setOK(ResultCode.CODE_STATE_200,"",pathBack);
			 materialInfoVo.setOverShare(true);
			 shareMaterialInfoDao.updateById(materialInfoVo);
		 }
		 return result;
	}
	
	@Override
	public Result shareMaterialInfoShar(ShareMaterialInfoSearch search, Result result, SystemUsers users,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		ShareMaterialInfoVo materialInfoVo = shareMaterialInfoDao.selectByIdJoin(search.getMaterialInfoId());
		if(materialInfoVo == null) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		ShareMaterial shareMaterial = materialInfoVo.getMaterialVo();
		if(shareMaterial == null) {
			result.setError("该素材的上级素材不存在或者已删除，分享失败");
			return result;
		}
		ImageRenderer renderer = new ImageRenderer();
		//http://localhost:8080/tauto/interfaceShop/shareMaterialInfo/shareMaterialInfoSharHtml?sessionId=09219b6954edd90e113c7e5d00416e23&materialInfoId=1
		
//		String url = "http://localhost:8080/tauto/share.jsp?materialName="+shareMaterial.getMaterialName()+"&baseImage="+shareMaterial.getImage()+"&image="+
//				materialInfoVo.getImage()+"&remarks"+materialInfoVo.getRemarks()+"&userName="+users.getRealName()+"&phone="+users.getPhoneNumber()+"&orgName="+
//				organization.getShortName()+"&headPortrait"+users.getHeadPortrait()+"&ticketImage"+users.getTicketImage();
//		String url = "http://tomcat.mifengqiche.com/tauto/interfaceShop/shareMaterialInfo/shareMaterialInfoSharHtml?sessionId="+users.getSessionId()+
//				"&materialInfoId="+materialInfoVo.getMaterialInfoId();
		String url = "http://localhost:8080/tauto/emInterface/employee/shop/shareMaterialInfoSharHtml?sessionId="+users.getSessionId()+"&materialInfoId="+
				materialInfoVo.getMaterialInfoId();
		String path = SystemPath.ROOT_PATH+File.separator+"res"+File.separator+"image"+File.separator+users.getUsersId()+".png";
		File file = new File(path);
		if(!file.exists()){
			//先得到文件的上级目录，并创建上级目录，在创建文件
			file.getParentFile().mkdir();
			try {
				//创建文件
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream out = new FileOutputStream(file);
		//生成网页截图
		if(!renderer.renderURL(url, out, ImageRenderer.Type.PNG)) {
			result.setError("生成分享截图失败");
			return result;
		}
		String pathBack = QiniuUtils.uploadFileBackPath(FileInterchangeBytes.getBytes(path), System.currentTimeMillis()+"", null);//默认未images空间
		if(StringUtil.isEmpty(pathBack)){
			System.out.println("上传文件出错");
			result.setError(ResultCode.CODE_STATE_4009, "生成分享截图失败");
		}else{
			//删除旧文件
			file.delete();
			result.setOK(ResultCode.CODE_STATE_200,"",pathBack);
			materialInfoVo.setOverShare(true);
			shareMaterialInfoDao.updateById(materialInfoVo);
		}
		return result;
	}

	@Override
	public Result shareMaterialInfoInfo(ShareMaterialInfoSearch search, Result result, SystemUsers users)
			throws Exception {
		ShareMaterialInfoVo materialInfoVo = shareMaterialInfoDao.selectByIdJoin(search.getMaterialInfoId());
		if(materialInfoVo == null || materialInfoVo.getOverDelete()) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", materialInfoVo);
		return result;
	}

}
