package com.onesite.utilities;

import java.util.SplittableRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomNumberUtil {
	private static final Logger LOGGER = LogManager.getLogger(RandomNumberUtil.class);
	private static RandomNumberUtil randomNumUtil;
	
	public static RandomNumberUtil getRandomNumberUtil(){
		if(randomNumUtil == null){	return randomNumUtil = new RandomNumberUtil();
		}else{return randomNumUtil;
		}
	}
	public static void setRandomNumberUtilInstanceToNull(){
		randomNumUtil = null;
	}
	public int getRandomNumber(){
		LOGGER.info("------- generating random number --------");
		return new SplittableRandom().nextInt(0, 99000);
	}

}
