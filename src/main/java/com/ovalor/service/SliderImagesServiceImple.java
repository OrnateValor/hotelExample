package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.SliderImagesMapper;
import com.ovalor.vo.SliderImagesVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SliderImagesServiceImple implements SliderImagesService {
	private SliderImagesMapper siMapper;

	// getList
	@Override
	public ArrayList<SliderImagesVo> getSliderImagesList() {
		return siMapper.getSliderImgList();
	}

	@Override
	public ArrayList<SliderImagesVo> getAllSliderImagesList() {
		// TODO Auto-generated method stub
		return siMapper.getAllSliderImgList();
	}

	// Select
	@Override
	public SliderImagesVo getSliderImages(int no) {
		return siMapper.getSliderImg(no);
	}

	// Insert
	@Override
	public int addSliderImages(SliderImagesVo img) {
		// TODO Auto-generated method stub
		return siMapper.addSliderImg(img);
	}

	// Update
	@Override
	public int modSliderImages(SliderImagesVo img) {
		return siMapper.modSliderImg(img);
	}

	@Override
	public int inActive(SliderImagesVo img) {
		// TODO Auto-generated method stub
		return siMapper.inActive(img);
	}

	// Delete
	@Override
	public int del(int no) {
		// TODO Auto-generated method stub
		return siMapper.del(no);
	}

}
