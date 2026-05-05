package com.sohatec;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBProvider {
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabaseJuniorStaff() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://YOUR_USER_NAME:YOUR_PASSWORD@localhost:YOUR_PORT/?authSource=admin");
        }
        return mongoClient.getDatabase("JuniorStaff");
    }

    public static MongoDatabase getDatabaseSeniorStaff() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://YOUR_USER_NAME:YOUR_PASSWORD@localhost:YOUR_PORT/?authSource=admin");
        }
        return mongoClient.getDatabase("SeniorStaff");
    }

    public static void saveJuniorStaffToMongo(String juniorId, String content) {
        try {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseJuniorStaff().getCollection("Content");
            Document doc = new Document("juniorId", juniorId)
                    .append("content", content)
                    .append("timestamp", new java.util.Date());

            collection.insertOne(doc);
            System.out.println("=== Storage Content To MongoDB With UserId: " + juniorId + " ===");
        } catch (Exception e) {
            System.err.println("=== Error Of MongoDB: " + e.getMessage() + " ===");
        }
    }

    public static void saveSeniorStaffToMongo(String seniorId, String content) {
        try {
            MongoCollection<Document> collection = MongoDBProvider.getDatabaseSeniorStaff().getCollection("Content");
            Document doc = new Document("seniorId", seniorId)
                    .append("content", content)
                    .append("timestamp", new java.util.Date());

            collection.insertOne(doc);
            System.out.println("=== Storage Content To MongoDB With UserId: " + seniorId + " ===");
        } catch (Exception e) {
            System.err.println("=== Error Of MongoDB: " + e.getMessage() + " ===");
        }
    }
}
