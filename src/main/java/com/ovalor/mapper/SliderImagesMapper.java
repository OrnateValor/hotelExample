package com.ovalor.mapper;

import java.util.ArrayList;

import com.ovalor.vo.SliderImagesVo;

public interface SliderImagesMapper {
	// GetList
	public ArrayList<SliderImagesVo> getSliderImgList();

	// Select
	public SliderImagesVo getSliderImg(int no);

	// Insert
	public int addSliderImg(SliderImagesVo sliderImages);

	// Update
	public int modSliderImg(SliderImagesVo sliderImages);
}
