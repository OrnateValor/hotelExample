package com.ovalor.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.vo.SliderImagesVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SliderImagesMapperTests {

	@Setter(onMethod_ = @Autowired)
	private SliderImagesMapper siMapper;

	// GetList
//	@Test
	public void testGetSliderImagesList() {
		siMapper.getSliderImgList().forEach(SliderImg -> log.info(SliderImg));
	}

	// Select
//	@Test
	public void testGetSliderImages() {
		SliderImagesVo sliderImg = siMapper.getSliderImg(4);
		log.info(sliderImg);
	}

	// Insert
//	@Test
	public void testAddSliderImages() {
		SliderImagesVo sliderImg = new SliderImagesVo();

		sliderImg.setFileName("testImg.jpg");
		sliderImg.setSortNo("8");
		
		siMapper.addSliderImg(sliderImg);
		log.info(sliderImg);
	}

	// Update
	@Test
	public void testModSliderImages() {
		SliderImagesVo sliderImages = siMapper.getSliderImg(4);
		sliderImages.setDeleteFlag('1');
		siMapper.modSliderImg(sliderImages);
		log.info(sliderImages);
	}
}
