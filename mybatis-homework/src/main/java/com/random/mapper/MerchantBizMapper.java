package com.random.mapper;

import java.util.List;

import com.random.domain.MerchantBiz;

/**
 * 
 * Merchant biz mapper
 * 
 * @author Random
 *
 */
public interface MerchantBizMapper {
	public List<MerchantBiz> findAll();
    public MerchantBiz findByMerId(int merId);
    public List<Integer> findBizCodeByMerId(int merId);
    
    public int insertOne(MerchantBiz merchantBiz);
    public int insertBizList(List<MerchantBiz> merchantBizList);
    
    public int updateOne(MerchantBiz merchantBiz);
    public int updateBizList(List<MerchantBiz> merchantBizList);
    
    public int deleteOne(MerchantBiz merchantBiz);
    public int deleteBizList(List<MerchantBiz> merchantBizList);
}
