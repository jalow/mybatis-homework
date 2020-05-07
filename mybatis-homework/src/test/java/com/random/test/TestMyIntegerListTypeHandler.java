package com.random.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.random.domain.MerchantBiz;
import com.random.mapper.MerchantBizMapper;

public class TestMyIntegerListTypeHandler {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }   
	
	
	@Test
	public void test() {
		
		SqlSession session = sqlSessionFactory.openSession();
		MerchantBizMapper merchantBizMapper = session.getMapper(MerchantBizMapper.class);
		//MerchantBiz merchantBiz = merchantBizMapper.findByMerId(1);
		//List<MerchantBiz> merchantBizList = merchantBizMapper.findAll();
		
		MerchantBiz insertMerchantBiz = new MerchantBiz();
		insertMerchantBiz.setMerId(4);
		List<Integer> bizCodeList = new ArrayList<Integer>();
		bizCodeList.add(8);
		bizCodeList.add(9);
		bizCodeList.add(10);
		bizCodeList.add(11);
		bizCodeList.add(222);
		insertMerchantBiz.setBizCode(bizCodeList);
		
		List<MerchantBiz> merchantBizList = new ArrayList<MerchantBiz>();
		merchantBizList.add(insertMerchantBiz);
		//int instListCount = merchantBizMapper.insertBizList(merchantBizList);
		//int instCount = merchantBizMapper.insertOne(insertMerchantBiz);
		
		//int updListCount = merchantBizMapper.updateBizList(merchantBizList);
		//int updCount = merchantBizMapper.updateOne(merchantBiz);
		
		//int delCount = merchantBizMapper.deleteOne(insertMerchantBiz);
		int delListCount = merchantBizMapper.deleteBizList(merchantBizList);
		
		session.commit();
		System.out.println(delListCount);
	}
}
