package com.wxg.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
	 private static MongoDatabase database;
	 public static MongoDatabase getMongoDBConnect(){
		 try {
			 	//连接到MongoDB服务 如果是远程连接可以替换“192.168.168.128”为服务器所在IP地址  
	            //ServerAddress()两个参数分别为 服务器地址 和 端口  
	            /*ServerAddress serverAddress = new ServerAddress("192.168.168.128",27017);  
	            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	            addrs.add(serverAddress);  
	            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码  
	            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());  
	            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	            credentials.add(credential);  
	            //通过连接认证获取MongoDB连接  
	            MongoClient mongoClient = new MongoClient(addrs,credentials); */ 

				//连接到 mongodb 服务
				MongoClient client = new MongoClient("192.168.168.128",27017);
				
				//连接到数据库
				database = client.getDatabase("ds_db");
				System.out.println("########Connect to database successfully##########---MongoDB");
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		 	return database;
	 }
}
