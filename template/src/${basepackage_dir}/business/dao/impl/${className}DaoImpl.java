<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.file.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.buick.activity.file.po.*;
import com.buick.activity.file.po.query.*;
import ${basepackage}.file.dao.*;

import com.buick.activity.base.dao.BaseDao;

@Repository(value="${table.sqlName}Dao")
public class ${className}DaoImpl extends BaseDao implements ${className}Dao{
	
	@Override
	public int insert (${className} ${table.sqlName}){
		return insert("${table.sqlName}Dao.insert",${table.sqlName});
	}
	
	@Override
	public int delete(Long id){
		return delete("${table.sqlName}Dao.delete",id);
	}
	
	@Override
	public  int update(${className} ${table.sqlName}){
		return update("${table.sqlName}Dao.update",${table.sqlName});
	}
	
	@Override
	public ${className} getById(Long id){
		return (${className})getReadSqlSession().selectOne("${table.sqlName}Dao.getById",id);
	}
	
	@Override
	public Integer getCountByQuery(${className}Query query){
		return getReadSqlSession().selectOne("${table.sqlName}Dao.getCountByQuery",query);
	}
	
	@Override
	public List<${className}> getInfoByQuery(${className}Query query){
		return getReadSqlSession().selectList("${table.sqlName}Dao.getInfoByQuery",query);
	}
	
	@Override
	public List<${className}> getListByQuery(${className}Query query){
		return getReadSqlSession().selectList("${table.sqlName}Dao.getListByQuery",query);
	}
}
