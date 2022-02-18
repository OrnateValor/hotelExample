package com.ovalor.mapper;

import java.util.ArrayList;

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
	public int delOptions(int no);

	// inActive
	public int inActive(OptionsVo options);

}
