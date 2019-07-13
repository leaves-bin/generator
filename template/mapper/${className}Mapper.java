<#include "/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.mapper;

import com.leaves.base.BaseMapper;
import ${basepackage}.po.${className};
import ${basepackage}.po.query.${className}Query;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: yongbin.zhang
 * @date: <#if now??>${now?string('yyyy-MM-dd')}</#if>
 * @desc: ${table.remarks}
 */
@Mapper
@Repository
public interface ${className}Mapper extends BaseMapper<${className}, ${className}Query> {
	
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