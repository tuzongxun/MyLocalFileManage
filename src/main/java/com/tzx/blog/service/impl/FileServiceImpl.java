package com.tzx.blog.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tzx.blog.model.FileModel;
import com.tzx.blog.service.FileService;

/**
 * 从文件系统获取文章内容
 * 
 * @author tzx
 * @date 2018年3月28日 下午11:24:10
 *
 */
@Service
public class FileServiceImpl implements FileService {

	@Value("${fileRootPath}")
	private String fileRootPath;

	@Override
	public Map<String, Object> readFiles() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FileModel> fileModels = new ArrayList<FileModel>();
		Set<String> set = new HashSet<String>();
		File file = new File(fileRootPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 文件名格式：type_标题
			for (File f : files) {
				FileModel fileModel = new FileModel();
				String fileName = f.getName();
				fileModel.setFileName(fileName.substring(
						fileName.indexOf("_") + 1, fileName.indexOf(".")));
				fileModel.setFilePath(f.getPath());
				String fileType = fileName.substring(0, fileName.indexOf("_"));
				fileModel.setFileType(fileType);
				set.add(fileType);
				// StringBuffer str = new StringBuffer();
				// // FileReader fileReader = null;
				// BufferedReader br = null;
				// try {
				// // fileReader = new FileReader(f);
				// br = new BufferedReader(new InputStreamReader(
				// new FileInputStream(f), "utf-8"));
				// while (br.ready()) {
				// String string = br.readLine();
				// if (string.startsWith("## ")) {
				// // 一级标题
				// string = string.replace("## ", "<hr><h1>");
				// string += "</h1>";
				// } else if (string.startsWith("### ")) {
				// string = string.replace("### ", "<h2>");
				// string += "</h2>";
				// } else if (string.startsWith("#### ")) {
				// string = string.replace("#### ", "<h3>");
				// string += "</h3>";
				// }
				// str.append(string).append("<br>");
				// }
				// br.close();
				// // fileReader.close();
				// } catch (FileNotFoundException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// } finally {
				// try {
				// br.close();
				// // fileReader.close();s
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// }
				// fileModel.setFileContent(str.toString());
				fileModels.add(fileModel);
			}
		} else {
			return null;
		}
		map.put("fileModels", fileModels);
		map.put("typeList", set.toArray());
		return map;
	}

	@Override
	public List<FileModel> findTypeList(String type) {
		List<FileModel> fileModels = new ArrayList<FileModel>();
		Set<String> set = new HashSet<String>();
		File file = new File(fileRootPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 文件名格式：type_标题
			for (File f : files) {
				if (f.getName().startsWith(type)) {
					FileModel fileModel = new FileModel();
					String fileName = f.getName();
					fileModel.setFileName(fileName.substring(
							fileName.indexOf("_") + 1, fileName.indexOf(".")));
					fileModel.setFilePath(f.getPath());
					String fileType = fileName.substring(0,
							fileName.indexOf("_"));
					fileModel.setFileType(fileType);
					set.add(fileType);
					fileModels.add(fileModel);
				}
			}
		}
		return fileModels;
	}

}
