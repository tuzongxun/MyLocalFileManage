package com.tzx.blog.service;

import java.util.List;
import java.util.Map;

import com.tzx.blog.model.FileModel;

/**
 * 文件操作service
 * 
 * @author tzx
 * @date 2018年3月23日 下午3:02:22
 *
 */
public interface FileService {
	public Map<String, Object> readFiles();

	public List<FileModel> findTypeList(String type);
}
