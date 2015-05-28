package com.gtfs.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ShowBeanNameProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object object, String name)throws BeansException {
		
		
		
		return object;
	}

	@Override
	public Object postProcessBeforeInitialization(Object object, String name)throws BeansException {
		
		System.out.println("Before Creating "+name);
		
		return object;
	}

}
