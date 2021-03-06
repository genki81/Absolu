package org.absolu.dao;

import org.absolu.battle.api.pojo.Personnage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class CharacterDao extends GenericDao {
	private static final long serialVersionUID = -3031341743624117651L;
	private final static Logger LOGGER = LogManager.getLogger(CharacterDao.class);

	public void cleanCharacters() {
		DBCollection coll = connection.getMongoDb().getCollection("personnages");
		coll.drop();
	}

	public boolean saveCharacter(Personnage p) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DBObject dbPersonnage = mapper.convertValue(p, BasicDBObject.class);
			DBCollection coll = connection.getMongoDb().getCollection("personnages");

			BasicDBObject dbQuery = new BasicDBObject().append("realm", p.getRealm()).append("name", p.getName());
			DBObject old = coll.findAndModify(dbQuery, dbPersonnage);
			if (old == null) {
				coll.save(dbPersonnage);
			}

			return true;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}

	public Personnage findCharacter(String realm, String name) {
		Personnage p = new Personnage();
		try {
			DBCollection coll = connection.getMongoDb().getCollection("personnages");

			BasicDBObject dbQuery = new BasicDBObject().append("realm", realm).append("name", name);
			DBObject obj = coll.findOne(dbQuery);
			if (obj != null) {
				obj.removeField("_id");
				ObjectMapper mapper = new ObjectMapper();
				p = mapper.convertValue(obj, Personnage.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return p;
	}
}
