package com.bruce.generator;

/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		GeneratorFacade g = new GeneratorFacade();
		//g.printAllTableNames();				//打印数据库中的表名称
		//List<Table> tableList=g.getAllTableNames();
		g.getGenerator().setTemplateRootDir("E:\\ideaspace\\generator\\template");
		g.getGenerator().setOutRootDir("E:\\ideaspace\\generator\\temp");
		g.deleteOutRootDir();
		
		String str="video,video_setting,video_type,video_type_relation";
		
		String[] tables=str.split(",");
		for (String string : tables) {
			g.generateByTable(string.trim().toLowerCase());
		}
		
//		for(Table table  :tableList)//删除生成器的输出目录
//		{
//			if(table.getPkCount()==0){
//				//System.out.println("alter table "+table.getSqlNayme()+" add constraint pk_"+table.getSqlName().toLowerCase()+" primary key(id);");
//			}else{
//				g.generateByTable(table.getSqlName());	
//			}
//		}
		System.out.println("完成");
		//通过数据库表生成文件,template为模板的根目录
		//g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");
		 
//		g.deleteByTable("table_name", "template"); //删除生成的文件
		//打开文件夹
//		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
		
	}
}
