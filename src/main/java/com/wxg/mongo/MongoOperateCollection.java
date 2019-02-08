package com.wxg.mongo;

import com.wxg.util.MongoUtil;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoOperateCollection {
	private static MongoDatabase database;
	
	MongoOperateCollection(){
		database  = MongoUtil.getMongoDBConnect();
	}
	
	//创建集合
	public void createCollections(String collectNm){
//		MongoDatabase database  = MongoUtil.getMongoDBConnect();  //获取数据库连接
		try {
			database.createCollection(collectNm.toString());
			System.out.println("集合:"+collectNm+"创建成功！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	//获取集合
	public void getCollections(String collectNm){
		try {
			MongoCollection<Document> collection = database.getCollection(collectNm.toString());
			System.out.println("获取集合:"+collectNm+"成功！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	//插入文档
	public void insertDocument(String collectNm){
		try {
			//插入文档  
         /** 
         * 1. 创建文档 org.bson.Document 参数为key-value的格式 
         * 2. 创建文档集合List<Document> 
         * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
         * */
			MongoCollection<Document> collection = database.getCollection(collectNm.toString());
			Document document = new Document("title", "MongoDB").append("description", "database").  
		         append("likes", 100).append("by", "Fly"); 
			List<Document> documents = new ArrayList<Document>();
			documents.add(document);
			collection.insertMany(documents);
			System.out.println("文档插入成功！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	//查询文档
	public void selectDocument(String collectNm){
        try {
        	//检索所有文档  
            /** 
            * 1. 获取迭代器FindIterable<Document> 
            * 2. 获取游标MongoCursor<Document> 
            * 3. 通过游标遍历检索出的文档集合 
            * */  
//        	database  = MongoUtil.getMongoDBConnect();
    		MongoCollection<Document> collection = database.getCollection(collectNm.toString());
    		FindIterable<Document> findIterable = collection.find();  
            MongoCursor<Document> mongoCursor = findIterable.iterator();  
            while(mongoCursor.hasNext()){  
               System.out.println(mongoCursor.next());  
            }  
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	//更新文档
	public void updateDocument(String collectNm){
		try {
        	//更新文档  将文档中title=Java 教程的文档修改为by=wxg  
    		MongoCollection<Document> collection = database.getCollection(collectNm.toString());
    		collection.updateMany(Filters.eq("title", "Java 教程"), new Document("$set",new Document("by","wxg")));
    		System.out.println("文档更新成功！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	//删除文档
	public void deleteDocument(String collectNm){
		try {
        	//更新文档  将文档中title=Java 教程的文档修改为by=wxg  
    		MongoCollection<Document> collection = database.getCollection(collectNm.toString());
    		//删除符合条件的第一个文档  
    		collection.deleteOne(Filters.eq("title", "Java 教程"));
    		//删除所有符合条件的文档  
//    		collection.deleteMany(Filters.eq("title", "Java 教程"));
    		FindIterable<Document> findIterable = collection.find();
    		MongoCursor<Document> mongoCursor = findIterable.iterator();
    		while(mongoCursor.hasNext()){
    			System.out.println(mongoCursor.next());
    		}
    		
    		System.out.println("文档删除成功！");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
	}
	
}
