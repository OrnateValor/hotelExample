package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.OptionsMapper;
import com.ovalor.vo.OptionsVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OptionsServiceImple implements OptionsService {
	private OptionsMapper oMapper;

	// GetList
	@Override
	public ArrayList<OptionsVo> getOptionsList() {
		return oMapper.getOptionsList();
	}

	// Select
	@Override
	public OptionsVo getOptions(int no) {
		// TODO Auto-generated method stub
		return oMapper.getOptions(no);
	}

	// Insert
	@Override
	public int addOptions(OptionsVo option) {
		// TODO Auto-generated method stub
		return oMapper.addOptions(option);
	}

	// Update
	@Override
	public int modOptions(OptionsVo option) {
		// TODO Auto-generated method stub
		return oMapper.modOptions(option);
	}

	// Delete
	@Override
	public int del(int no) {
		// TODO Auto-generated method stub
		return oMapper.delOptions(no);
	}

	@Override
	public int inActive(OptionsVo options) {
		// TODO Auto-generated method stub
		return oMapper.inActive(options);
	}
}
