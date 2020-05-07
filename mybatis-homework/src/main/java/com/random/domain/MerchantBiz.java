package com.random.domain;

import java.util.List;

public class MerchantBiz {
    private int merId;
    private List<Integer> bizCode;
    
    
	public int getMerId() {
		return merId;
	}
	
	public void setMerId(int merId) {
		this.merId = merId;
	}
	
	public List<Integer> getBizCode() {
		return bizCode;
	}
	
	public void setBizCode(List<Integer> bizCode) {
		this.bizCode = bizCode;
	}

	@Override
	public String toString() {
		return "MerchantBiz [merId=" + merId + ", bizCode=" + bizCode + "]";
	}		
}
