<#include "/java_copyright.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.po.query;

import com.leaves.base.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


public class ${className}Query  extends BaseQuery implements Serializable{

	<#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};
	</#list>

}
