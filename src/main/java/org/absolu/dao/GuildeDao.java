package org.absolu.dao;

import org.absolu.battle.api.pojo.Guilde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GuildeDao extends GenericDao {

	public boolean saveGuilde(Guilde g) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DBObject dbGuilde = mapper.convertValue(g, BasicDBObject.class);
			DBCollection coll = connection.getMongoDb().getCollection("guilde");

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
