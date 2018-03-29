package com.tzx.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tzx.blog.model.FileModel;
import com.tzx.blog.service.FileService;

@Controller
@RequestMapping
public class BlogController {
	@Autowired
	FileService fileService;

	/**
	 * 首页查询
	 * 
	 * @author tzx
	 * @date 2018年3月29日 下午3:06:44
	 */
	@RequestMapping(value = "/getIndex")
	@ResponseBody
	public Object index() {
		// String content = "";
		// 文件列表
		// List<FileModel> fileModels = fileService.readFiles();
		Map<String, Object> fileMap = fileService.readFiles();
		// if (fileModels != null && fileModels.size() > 0) {
		// content = fileModels.get(0).getFileContent();
		// }
		System.out.println("my test");
		// Map<String, Object> map = new HashMap<String, Object>();
		// List<String> list = new ArrayList<String>();
		// list.add("java");
		// list.add("数据库");
		// map.put("typeList", list);
		// // map.put("content", content);
		// map.put("blogs", fileMap);
		return fileMap;
	}

	/**
	 * 根据类型查询该类型的博文
	 * 
	 * @author tzx
	 * @date 2018年3月29日 下午3:08:00
	 */
	@RequestMapping(value = "/getTypeList", method = RequestMethod.POST)
	@ResponseBody
	public Object getTypeList(@RequestBody String type) {
		System.out.println(type);
		List<FileModel> fileModels = fileService.findTypeList(type);
		return fileModels;
	}

	@RequestMapping(value = "/getBlogInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getBlogInfo(@RequestBody String filePath) {
		FileModel fileModel = fileService.findBlogInfo(filePath);
		return fileModel;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestBody String searchValue) {
		List<FileModel> fileModels = fileService.search(searchValue);
		return fileModels;
	}
}
