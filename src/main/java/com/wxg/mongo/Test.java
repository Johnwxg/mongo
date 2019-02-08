package com.wxg.mongo;

public class Test {

	public static void main(String[] args) {
		MongoOperateCollection mongo = new MongoOperateCollection();
//		mongo.updateDocument("runoob");
//		mongo.selectDocument("runoob");
//		mongo.insertDocument("runoob");
		mongo.deleteDocument("runoob");
	}
}
