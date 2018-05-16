<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.file.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.buick.activity.file.po.*;
import com.buick.activity.file.po.query.*;
import com.buick.activity.common.web.ActivityModelMap;

public interface ${className}Service {
	
	/**
	 * 页面初始化
	 */
	public void ${className}PageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建${className}
	 **/
	public Boolean save(HttpServletRequest request,${className} ${classNameLower},ActivityModelMap modelMap);
	
	/** 
	 * 更新${className}
	 **/	
    public Boolean update(HttpServletRequest request,${className} ${classNameLower},ActivityModelMap modelMap);
    
	/** 
	 * 删除${className}
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到${className}
	 **/    
    public ${className} get${className}(HttpServletRequest request,ActivityModelMap modelMap);
    
}
