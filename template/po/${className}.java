<#include "/java_copyright.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
/**
 * @author: yongbin.zhang
 * @date: <#if now??>${now?string('yyyy-MM-dd')}</#if>
 * @desc: ${table.remarks}
 */
@Getter
@Setter
public class ${className} implements Serializable {
	<#list table.columns as column>
	/**
	 * ${column.sqlName}${column.columnAlias!}
	 */
	private ${column.javaType} ${column.columnNameLower};
	</#list>

}
