package com.ducetech.app.dao;

import com.ducetech.app.model.PostSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PostSettingDAO {

	/**
	* @Title: selectPostSetting
	* @param dept
	* @return List<PostSetting>
	* @Description: PostSetting通用查询
	*/
	List<PostSetting> selectPostSetting(PostSetting dept);

	/**
	* @Title: addPostSetting
	* @param dept
	* @Description: 保存新增的人员信息
	*/
	void insertPostSetting(PostSetting dept);

	/**
	* @Title: updatePostSetting
	* @param dept
	* @Description: 更新人员信息
	*/
	void updatePostSetting(PostSetting dept);

	/**
	* @Title: deletePostSetting
	* @param positionCode
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deletePostSetting(@Param("positionCode") String positionCode, @Param("ifUse") int ifUse);

    /**
     * @Title: selectPostSettingByGroupName
     * @param positionCode
     * @return PostSetting
     * @Description: 按登部门名称查询
     */
    PostSetting selectPostSettingByPostCode(@Param("positionCode") String positionCode);

    List<PostSetting> selectByParentCode(@Param("positionCode") String parentCode, @Param("positionCodeLength") int positionCodeLength);

}