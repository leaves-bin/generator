<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.file.dao;
import java.util.List;
import com.buick.activity.file.po.*;
import com.buick.activity.file.po.query.*;


public interface ${className}Dao {
	
	/**
	 * 新增方法
	 */
	public int insert (${className} ${table.sqlName});
    
    
	/**
	 * 删除方法
	 */
	public int delete(Long id);
    
    /**
	 * 修改方法
	 */
	public  int update (${className} ${table.sqlName});

	/**
	 * 查询方法
	 */
    public ${className} getById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(${className}Query query);
    
    /**
     *按条件查询
     */
    public List<${className}> getInfoByQuery(${className}Query query);
    
    /**
     *按条件查询 不带分页
     */
    public List<${className}> getListByQuery(${className}Query query);

}