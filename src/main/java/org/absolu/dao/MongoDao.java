package org.absolu.dao;

import org.absolu.battle.api.pojo.Guilde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoDao {
	private DB mongoDb;
	private String mongoStatus;

	private final String mongoHost;
	private final String mongoSPort;
	private final String mongoUser;
	private final String mongoPwd;
	private final String mongoDbName;

	public MongoDao(String mongoHost, String mongoSPort, String mongoUser, String mongoPwd, String mongoDbName) {
		this.mongoHost = mongoHost;
		this.mongoSPort = mongoSPort;
		this.mongoUser = mongoUser;
		this.mongoPwd = mongoPwd;
		this.mongoDbName = mongoDbName;
		initConnection();
	}

	private void initConnection() {
		Mongo mongo = null;
		mongoDb = null;
		try {
			int mongoPort = Integer.decode(mongoSPort);

			mongo = new Mongo(mongoHost, mongoPort);
			mongoDb = mongo.getDB(mongoDbName);
			if (mongoDb.authenticate(mongoUser, mongoPwd.toCharArray()) == false) {
				mongoStatus = "Erreur d'authentification...";
			} else {
				mongoStatus = "OK !";
			}
		} catch (Exception e) {
			mongoStatus = "Erreur de connexion : " + e.getMessage();
			if (mongo != null) {
				try {
					mongo.close();
				} catch (Exception ex) {
				}
			}
		}

	}

	public DB getMongoDb() {
		return mongoDb;
	}

	public String getMongoStatus() {
		return mongoStatus;
	}

	public boolean saveGuilde(Guilde g) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DBObject dbGuilde = mapper.convertValue(g, BasicDBObject.class);
			DBCollection coll = mongoDb.getCollection("guilde");

			BasicDBObject dbQuery = new BasicDBObject().append("realm", g.getRealm()).append("name", g.getName());
			DBObject old = coll.findAndModify(dbQuery, dbGuilde);
			if (old == null) {
				coll.save(dbGuilde);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
