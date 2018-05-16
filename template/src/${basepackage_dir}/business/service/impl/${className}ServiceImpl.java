<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.file.service.impl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import com.buick.activity.file.po.*;
import com.buick.activity.file.po.query.*;
import com.buick.activity.file.dao.*;
import com.buick.activity.file.service.*;
import com.buick.activity.common.web.ActivityModelMap;

@Service("${table.sqlName}Service")
public class ${className}ServiceImpl implements ${className}Service {

	@Resource
	private ${className}Dao ${table.sqlName}Dao;
	
	/**
	 * 页面初始化
	 */
	@Override
	public void ${className}PageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建${className}
	 **/
	@Override
	public Boolean save(HttpServletRequest request,${className} ${classNameLower},ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新${className}
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,${className} ${classNameLower},ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除${className}
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到${className}
	 **/
	@Override   
    public ${className} get${className}(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
}
