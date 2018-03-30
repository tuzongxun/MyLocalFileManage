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
	/**
	 * 首页文件列表
	 *
	 * @author tzx
	 * @date 2018年3月30日 上午10:04:57
	 */
	public Map<String, Object> readFiles();

	/**
	 * 条件查询文件列表
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午10:05:15
	 */
	public Map<String, Object> findTypeList(String type);

	/**
	 * 根据文件信息查看具体文件内容
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午10:05:34
	 */
	public FileModel findBlogInfo(String filePath);

	/**
	 * 通过关键字搜索文件
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午10:05:54
	 */
	public Map<String, Object> search(String searchValue);

	/**
	 * 分页查询
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午11:39:23
	 */
	public List<FileModel> findPage(int page);
}
