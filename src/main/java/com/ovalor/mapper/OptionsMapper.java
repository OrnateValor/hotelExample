package com.ovalor.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.ovalor.vo.OptionsVo;

public interface OptionsMapper {
	// GetList
	public ArrayList<OptionsVo> getOptionsList();

	// Select
	public OptionsVo getOptions(int no);

	// Insert
	public int addOptions(OptionsVo option);

	// Update
	public int modOptions(OptionsVo option);

	// Delete - deleteFlag
	public int delOptions(@Param("no") int no, @Param("deleteFlag") char deleteFlag);

}
