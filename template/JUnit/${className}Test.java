<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;

import com.buick.activity.file.dao.*;
import com.buick.activity.file.service.*;

@Controller
public class ${className}Test extends BaseSpringTest {
	
	// 模拟request,response
	private MockHttpServletRequest request;
	private MockHttpServletResponse response; 
	
	// 注入Resource
	@Autowired
	private ${className}Dao ${table.sqlName}Dao;
	
	// 执行测试方法之前初始化模拟request,response
	@Before  
    public void setUp(){  
        request = new MockHttpServletRequest();    
        request.setCharacterEncoding("UTF-8");    
        response = new MockHttpServletResponse();    
    }  
	
	@Test
	public void test() {
		/**
		 * 测试内容
		 */
	}

}

