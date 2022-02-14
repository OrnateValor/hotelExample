package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.OptionsVo;

public interface OptionsService {
	// GetList
	public ArrayList<OptionsVo> getOptionsList();

	// Select
	public OptionsVo getOptions(int no);

	// Insert
	public int addOptions(OptionsVo option);

	// Update
	public int modOptions(OptionsVo option);

	// Delete
	public int delOptions(int no, char deleteFlag);
}
