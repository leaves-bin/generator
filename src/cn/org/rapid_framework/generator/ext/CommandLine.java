package cn.org.rapid_framework.generator.ext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfig;
import cn.org.rapid_framework.generator.ext.tableconfig.model.TableConfigSet;
import cn.org.rapid_framework.generator.provider.db.sql.model.Sql;
import cn.org.rapid_framework.generator.util.ArrayHelper;
import cn.org.rapid_framework.generator.util.BeanHelper;
import cn.org.rapid_framework.generator.util.StringHelper;
import cn.org.rapid_framework.generator.util.SystemHelper;
/**
 * 命令行工具类,可以直接运行
 * 
 * @author badqiu
 */
public class CommandLine {
	
	public static void main(String[] args) throws Exception {
		//disable freemarker logging
		freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
	//	GenUtils.genByTable(Helper.createGeneratorFacade(dir_dal_output_root,"${dir_templates_root}/table/dalgen_config","${dir_templates_root}/share/dal"),genInputCmd)
		startProcess();
	}

	private static void startProcess() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("templateRootDir:"+new File(getTemplateRootDir()).getAbsolutePath());
		printUsages();
		while(sc.hasNextLine()) {
			try {
				processLine(sc);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				Thread.sleep(700);
				printUsages();
			}
		}
	}

	private static void processLine(Scanner sc) throws Exception {
		GeneratorFacade facade = new GeneratorFacade();
		String cmd = sc.next();
		if("gen".equals(cmd)) {
			String[] args = nextArguments(sc);
			if(args.length == 0) return;
			facade.getGenerator().setIncludes(getIncludes(args,1));
			facade.getGenerator().addTemplateRootDir(new File(getTemplateRootDir()));
			facade.generateByTable(args[0]);
			if(SystemHelper.isWindowsOS) {
			    Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot").replace('/', '\\'));
			}
		}else if("del".equals(cmd)) {
			String[] args = nextArguments(sc);
			if(args.length == 0) return;
			facade.getGenerator().setIncludes(getIncludes(args,1));
			facade.getGenerator().addTemplateRootDir(new File(getTemplateRootDir()));
			facade.deleteByTable(args[0]);
		}else if("quit".equals(cmd)) {
		    System.exit(0);
		}else {
			System.err.println(" [ERROR] unknow command:"+cmd);
		}
	}

	private static String getIncludes(String[] args, int i) {
		String includes = ArrayHelper.getValue(args, i);
		if(includes == null) return null;
		return includes.indexOf("*") >= 0 || includes.indexOf(",") >= 0 ? includes : includes+"/**";
	}
	
	private static String getTemplateRootDir() {
		return System.getProperty("templateRootDir", "template");
	}

	private static void printUsages() {
		System.out.println("Usage:");
		System.out.println("\tgen table_name [include_path]: generate files by table_name");
		System.out.println("\tdel table_name [include_path]: delete files by table_name");
		System.out.println("\tgen * [include_path]: search database all tables and generate files");
		System.out.println("\tdel * [include_path]: search database all tables and delete files");
		System.out.println("\tquit : quit");
		System.out.println("\t[include_path] subdir of templateRootDir,example: 1. dao  2. dao/**,service/**");
		System.out.print("please input command:");
	}
	
	private static String[] nextArguments(Scanner sc) {
		return StringHelper.tokenizeToStringArray(sc.nextLine()," ");
	}
	
	
	public static class GenUtils {
		public  void genByTableConfigSet(GeneratorFacade generatorFacade,TableConfigSet tableConfigSet) throws Exception {
			Map map = new HashMap();
			map.putAll(BeanHelper.describe(tableConfigSet));
			map.put("tableConfigSet", tableConfigSet);
			generatorFacade.generateByMap(map);
		}
		
		public void genByTableConfig(GeneratorFacade generatorFacade,TableConfigSet tableConfigSet,String tableSqlName) throws Exception {
			
			Collection<TableConfig> tableConfigs = Helper.getTableConfigs(tableConfigSet,tableSqlName);
			for(TableConfig tableConfig : tableConfigs) {
				Map map = new HashMap();
				String[] ignoreProperties = {"sqls"};
		        map.putAll(BeanHelper.describe(tableConfig,ignoreProperties));
		        map.put("tableConfig", tableConfig);
				generatorFacade.generateByMap(map);
			}
		}
		
		public void genByOperation(GeneratorFacade generatorFacade,TableConfigSet tableConfigSet,String tableSqlName) throws Exception {
			Collection<TableConfig> tableConfigs = Helper.getTableConfigs(tableConfigSet,tableSqlName);
			for(TableConfig tableConfig : tableConfigs) {
				for(Sql sql : tableConfig.getSqls()) {
		            Map operationMap = new HashMap();
		            operationMap.putAll(BeanHelper.describe(sql));
		            operationMap.put("sql", sql);
		            operationMap.put("tableConfig", tableConfig);
		            operationMap.put("basepackage", tableConfig.getBasepackage());
					generatorFacade.generateByMap(operationMap);
		        }
	        }
		}
		
		public void genByTable(GeneratorFacade generatorFacade,String tableSqlName) throws Exception {
			generatorFacade.generateByTable(tableSqlName);
		}

	}
	
	
	public static class Helper {
		public  List<String> getTableConfigFiles(File basedir) {
			String[] tableConfigFilesArray = basedir.list();
			List<String> result = new ArrayList();
			for(String str : tableConfigFilesArray) {
				if(str.endsWith(".xml")) {
					result.add(str);
				}
			}
			return result;
		}
		public static  Collection<TableConfig> getTableConfigs(TableConfigSet tableConfigSet,String tableSqlName) {
			if("*".equals(tableSqlName)) {
				return tableConfigSet.getTableConfigs();
			}else {
				TableConfig tableConfig = tableConfigSet.getBySqlName(tableSqlName);
				if(tableConfig == null) {
					throw new RuntimeException("根据tableName:${tableSqlName}没有找到相应的配置文件");
				}
				return Arrays.asList(tableConfig);
			}
		}
		
		public GeneratorFacade createGeneratorFacade(String outRootDir,String... templateRootDirs) {
		    if(templateRootDirs == null) throw new IllegalArgumentException("templateRootDirs must be not null");
		    if(outRootDir == null) throw new IllegalArgumentException("outRootDir must be not null");
		    
		    GeneratorFacade gf = new GeneratorFacade();
		    gf.getGenerator().setTemplateRootDirs(templateRootDirs);
		    gf.getGenerator().setOutRootDir(outRootDir);
		    return gf;
		}
			
	}
}
