package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.SliderImagesVo;

public interface SliderImagesService {

	ArrayList<SliderImagesVo> getSliderImagesList();
	
	ArrayList<SliderImagesVo> getAllSliderImagesList();

	SliderImagesVo getSliderImages(int no);
	
	int addSliderImages(SliderImagesVo img);

	int modSliderImages(SliderImagesVo img);

	int delSliderImages(int no);

}
