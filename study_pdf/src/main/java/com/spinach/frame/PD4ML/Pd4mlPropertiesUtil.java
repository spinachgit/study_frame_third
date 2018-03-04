package com.spinach.frame.PD4ML;

import java.io.File;
import java.io.FileFilter;

import com.spinach.frame.utils.FileUtils;

public class Pd4mlPropertiesUtil {
	public static void main(String[] args) throws Exception {
		//GeneratePd4mlProperties("src/main/resources/pd4ml_fonts/fonts");
		GeneratePd4mlProperties("/Library/Fonts/Microsoft");
		//GeneratePd4mlProperties("/System/Library/Fonts");
	}
	/**
	 * PD4ML字体映射文件生成工具
	 * @author:whh
	 * @date:2018年3月4日上午11:44:42
	 * @param fontsParentPath:字体的文件夹：注意是文件夹，不是具体文件路径
	 * 		  属性文件输出文件夹必须和字体文件夹一样，文件名必须为：pd4fonts.properties
	 * @throws Exception 
	 */
	public static void GeneratePd4mlProperties(String fontsPath) throws Exception {
		File file = new File(fontsPath);
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				String name = pathname.getName();
				if(name.endsWith(".ttf")){
					return true;
				}else{
					/**
					 * 删除不符合条件的文件，如果系统路径，不能打开，如果是自己拷贝的路径，可以。
					 */
					//pathname.delete();
				}
				return false;
			}
		});
		
		StringBuffer sbBuffer = new StringBuffer();
		
		for(File temp:files){
			/** 全名规则：Andale_Mono=Andale Mono.ttf **/
			/*sbBuffer.append(temp.getName().replaceAll(" ", "_")
					.replaceAll("-", "_"))
					.replaceAll("\\.ttf", "")
					.append("="+temp.getName()+"\n");*/
			/** 全名规则Andale Mono=Andale Mono.ttf **/
			sbBuffer.append(temp.getName()
					.replaceAll(" ", "\\\\ ")
					.replaceAll("\\.ttf", ""))
					.append("="+temp.getName()+"\n");
		}
		System.out.println("--------------开始----------------");
		System.out.println(sbBuffer.toString());
		FileUtils.writeFile(fontsPath+"/pd4fonts.properties", sbBuffer.toString());
		System.out.println("---------------结束---------------");
		
	}
}
