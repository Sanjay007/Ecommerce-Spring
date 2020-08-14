package com.ecom.app.utils;

import org.springframework.util.StringUtils;

import com.ecom.app.payload.ProductInput;

public class ValidationUtils {

	public static boolean validateProduct(ProductInput input) {

		if (StringUtils.isEmpty(input.getColor()) || StringUtils.isEmpty(input.getGender())
				|| StringUtils.isEmpty(input.getPrice()) || StringUtils.isEmpty(input.getProductName())) {
			return false;
		} else {
			return true;

		}
	}
}
