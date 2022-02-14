package com.ovalor.service;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.SliderImagesMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class SliderImagesServiceImple implements SliderImagesService {
	private SliderImagesMapper siMapper;
}
