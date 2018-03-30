package com.tzx.blog.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	@Value("${pageSize}")
	private int pageSize;

	@Override
	public Map<String, Object> readFiles() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FileModel> fileModels = new ArrayList<FileModel>();
		Set<String> set = new HashSet<String>();
		File file = new File(fileRootPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			map.put("fileCount", files.length);
			// 文件名格式：type_标题
			for (File f : files) {
				if (f.getName().contains("_")) {
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
			if (fileModels.size() > pageSize) {
				fileModels = fileModels.subList(0, pageSize);
			}

		} else {
			return null;
		}
		map.put("fileModels", fileModels);
		map.put("typeList", set.toArray());

		return map;
	}

	@Override
	public Map<String, Object> findTypeList(String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FileModel> fileModels = new ArrayList<FileModel>();
		File file = new File(fileRootPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			// 文件名格式：type_标题
			for (File f : files) {
				if (f.getName().startsWith(type) && f.getName().contains("_")) {
					FileModel fileModel = new FileModel();
					String fileName = f.getName();
					fileModel.setFileName(fileName.substring(
							fileName.indexOf("_") + 1, fileName.indexOf(".")));
					fileModel.setFilePath(f.getPath());
					String fileType = fileName.substring(0,
							fileName.indexOf("_"));
					fileModel.setFileType(fileType);
					fileModels.add(fileModel);
				}
			}
			map.put("fileCount", fileModels.size());
			if (fileModels.size() > pageSize) {
				fileModels = fileModels.subList(0, pageSize);
			}
		}
		map.put("fileModels", fileModels);
		return map;
	}

	@Override
	public FileModel findBlogInfo(String filePath) {
		File f = new File(filePath);
		FileModel fileModel = new FileModel();
		String fileName = f.getName();
		fileModel.setFileName(fileName.substring(fileName.indexOf("_") + 1,
				fileName.indexOf(".")));
		fileModel.setFilePath(f.getPath());
		String fileType = fileName.substring(0, fileName.indexOf("_"));
		fileModel.setFileType(fileType);
		StringBuffer str = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "utf-8"));
			while (br.ready()) {
				String string = br.readLine();
				if (string.startsWith("## ")) {
					// 一级标题
					string = string.replace("## ", "<hr><h1>");
					string += "</h1>";
				} else if (string.startsWith("### ")) {
					string = string.replace("### ", "<h2>");
					string += "</h2>";
				} else if (string.startsWith("#### ")) {
					string = string.replace("#### ", "<h3>");
					string += "</h3>";
				}
				str.append(string).append("<br>");
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		fileModel.setFileContent(str.toString());
		return fileModel;
	}

	/**
	 * 搜索方法，需要搜索文件名和内容
	 */
	@Override
	public Map<String, Object> search(String searchValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FileModel> fileModels = new ArrayList<FileModel>();
		Pattern pattern = Pattern.compile(searchValue);
		File file = new File(fileRootPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			fl: for (File f : files) {
				if (!f.getName().contains("_")) {
					continue;
				}
				Matcher matcher = pattern.matcher(f.getName());
				if (matcher.find()) {
					FileModel fileModel = new FileModel();
					String fileName = f.getName();
					fileModel.setFileName(fileName.substring(
							fileName.indexOf("_") + 1, fileName.indexOf(".")));
					fileModel.setFilePath(f.getPath());
					String fileType = fileName.substring(0,
							fileName.indexOf("_"));
					fileModel.setFileType(fileType);
					fileModels.add(fileModel);
					continue;
				} else {
					// StringBuffer str = new StringBuffer();
					BufferedReader br = null;
					try {
						br = new BufferedReader(new InputStreamReader(
								new FileInputStream(f), "utf-8"));
						while (br.ready()) {
							String line = br.readLine();
							Matcher m = pattern.matcher(line);
							if (m.find()) {
								FileModel fileModel = new FileModel();
								String fileName = f.getName();
								fileModel.setFileName(fileName.substring(
										fileName.indexOf("_") + 1,
										fileName.indexOf(".")));
								fileModel.setFilePath(f.getPath());
								String fileType = fileName.substring(0,
										fileName.indexOf("_"));
								fileModel.setFileType(fileType);
								fileModels.add(fileModel);
								continue fl;
							}
						}
						br.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			map.put("fileCount", fileModels.size());
			if (fileModels.size() > pageSize) {
				fileModels = fileModels.subList(0, pageSize);
			}
		}
		map.put("fileModels", fileModels);
		return map;
	}
}
