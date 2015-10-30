package org.absolu.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.Mongo;

@Service
@Scope("singleton")
public class MongoConnection {
	private DB mongoDb;
	private String mongoStatus;

	@Value("${mongoHost}")
	private String mongoHost;
	@Value("${mongoPort}")
	private String mongoSPort;
	@Value("${mongoUser}")
	private String mongoUser;
	@Value("${mongoPwd}")
	private String mongoPwd;
	@Value("${mongoDb}")
	private String mongoDbName;

	private boolean initialized;

	public MongoConnection() {
		initialized = false;
	}

	public void init() {
		Mongo mongo = null;
		mongoDb = null;
		initialized = false;
		try {
			int mongoPort = Integer.decode(mongoSPort);

			mongo = new Mongo(mongoHost, mongoPort);
			mongoDb = mongo.getDB(mongoDbName);
			if (mongoDb.authenticate(mongoUser, mongoPwd.toCharArray()) == false) {
				mongoStatus = "Erreur d'authentification...";
			} else {
				mongoStatus = "OK !";
				initialized = true;
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
		if (!initialized) {
			init();
		}
		return mongoDb;
	}

	public String getMongoStatus() {
		return mongoStatus;
	}

}
