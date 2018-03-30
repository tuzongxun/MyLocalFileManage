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
		Map<String, Object> fileMap = fileService.readFiles();
		System.out.println("my test");
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
		// List<FileModel> fileModels = fileService.findTypeList(type);
		Map<String, Object> fileMap = fileService.findTypeList(type);
		return fileMap;
	}

	/**
	 * 笔记内容查看
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午10:02:44
	 */
	@RequestMapping(value = "/getBlogInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getBlogInfo(@RequestBody String filePath) {
		FileModel fileModel = fileService.findBlogInfo(filePath);
		return fileModel;
	}

	/**
	 * 通过关键字搜索
	 * 
	 * @author tzx
	 * @date 2018年3月30日 上午10:03:15
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public Object search(@RequestBody String searchValue) {
		Map<String, Object> fileMap = fileService.search(searchValue);
		return fileMap;
	}

	@RequestMapping(value = "/getPages", method = RequestMethod.POST)
	@ResponseBody
	public Object getPages(@RequestBody Integer page) {
		List<FileModel> fileModels = fileService.findPage(page);
		return fileModels;
	}
}
