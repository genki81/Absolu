package org.absolu.dao;

import org.absolu.battle.api.pojo.Guilde;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GuildeDao extends GenericDao {
	private static final long serialVersionUID = 7432878201892046597L;
	private final static Logger LOGGER = LogManager.getLogger(GuildeDao.class);

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
			LOGGER.error(e);
			return false;
		}
	}

}
