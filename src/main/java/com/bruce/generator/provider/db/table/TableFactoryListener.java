package com.bruce.generator.provider.db.table;

import com.bruce.generator.provider.db.table.model.Table;
/**
 * TableFactory的监听器,可以拦截TableFactory的相关事件
 * @author badqiu
 *
 */
public interface TableFactoryListener {
	/**
	 * 当table创建完成时会触发该事件,可以在此修改Table的属性值什么的 
	 **/
	public void onTableCreated(Table table);
	
}