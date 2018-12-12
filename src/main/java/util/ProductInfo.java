package util;

public enum ProductInfo {
	CODE, PRODUCT, INPUT, OUTPUT, PRICE;
	
	public static ProductInfo getProductInfo(String productInfo)
	{
		return ProductInfo.valueOf(productInfo.replace("placeholder_", "").toUpperCase());
	}
}
