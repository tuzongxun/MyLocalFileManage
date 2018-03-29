package com.tzx.blog.model;

/**
 * 博客类
 * 
 * @author tzx
 * @date 2018年3月28日 下午10:52:54
 *
 */
public class BlogModel {

	private int id;
	/**
	 * 标题
	 */
	private String blogTitle;
	/**
	 * 类别
	 */
	private String blogCategy;

	/**
	 * 类型：原创/转载
	 */
	private String blogType;
	/**
	 * 标签
	 */
	private String blogMark;
	/**
	 * 保存位置：文件系统/数据库
	 */
	private String savePath;
	/**
	 * 博客内容
	 */
	private String blogContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogCategy() {
		return blogCategy;
	}

	public void setBlogCategy(String blogCategy) {
		this.blogCategy = blogCategy;
	}

	public String getBlogType() {
		return blogType;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public String getBlogMark() {
		return blogMark;
	}

	public void setBlogMark(String blogMark) {
		this.blogMark = blogMark;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	@Override
	public String toString() {
		return "BlogModel [id=" + id + ", blogTitle=" + blogTitle
				+ ", blogCategy=" + blogCategy + ", blogType=" + blogType
				+ ", blogMark=" + blogMark + ", savePath=" + savePath
				+ ", blogContent=" + blogContent + "]";
	}

}
