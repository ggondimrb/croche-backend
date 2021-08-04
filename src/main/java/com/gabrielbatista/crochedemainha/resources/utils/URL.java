package com.gabrielbatista.crochedemainha.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class URL {
	
	@Value("${s3.url.bucket.region}")
	private static String bucketRegion;
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
		if(!s.isEmpty()) {

			String[] vet = s.split(",");
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < vet.length; i++) {
				list.add(Integer.parseInt(vet[i]));
			}

			return list;
		}

		return null;
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeUrlImage(String name) {
		return "https://curso-spring-ionic-47.s3-sa-east-1.amazonaws.com/" + name;
	}

}
