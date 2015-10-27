package org.absolu.battle.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.absolu.WicketApplication;
import org.absolu.battle.api.pojo.Classe;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Race;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class BattleApiUtils {
	private final static Logger logger = LogManager.getLogger(BattleApiUtils.class);
	private final WicketApplication application;

	public BattleApiUtils(WicketApplication application) {
		this.application = application;
	};

	public Guilde getGuilde() {
		String readGuildMembersCount = queryBattle("https://eu.api.battle.net/wow/guild/Confrérie%20du%20Thorium/Absolu?fields=members&locale=fr_FR&jsonp=absolu&apikey=3pz4e2c2xjn2khrk8pruxc7munv9dk5m");
		readGuildMembersCount = readGuildMembersCount.substring(readGuildMembersCount.indexOf("(") + 1);
		readGuildMembersCount = readGuildMembersCount.substring(0, readGuildMembersCount.length() - 2);
		logger.debug(readGuildMembersCount);
		Guilde g = new Guilde();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(Guilde.class);
			g = reader.readValue(readGuildMembersCount);

			logger.info("Nombre de membres " + g.getMembers().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return g;
	}

	public List<Race> getListRaces() {
		String sRaces = queryBattle("https://eu.api.battle.net/wow/data/character/races?locale=fr_FR&apikey=3pz4e2c2xjn2khrk8pruxc7munv9dk5m");
		logger.debug(sRaces);
		List<Race> races = new ArrayList<Race>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Race>>() {
			}).withRootName("races");
			races = reader.readValue(sRaces);
			logger.debug("Nombre de races " + races.size());
			for (Race race : races) {
				logger.debug(race.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return races;
	}

	public List<Classe> getListClasses() {
		String sClasses = queryBattle("https://eu.api.battle.net/wow/data/character/classes?locale=fr_FR&apikey=3pz4e2c2xjn2khrk8pruxc7munv9dk5m");
		logger.debug(sClasses);
		List<Classe> classes = new ArrayList<Classe>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Classe>>() {
			}).withRootName("classes");
			classes = reader.readValue(sClasses);
			logger.debug("Nombre de races " + classes.size());
			for (Classe classe : classes) {
				logger.debug(classe.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	private String queryBattle(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				logger.error("Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public Classe getClasseById(int id) {
		for (Classe classe : application.getClasses()) {
			if (classe.getId() == id) {
				return classe;
			}
		}
		return new Classe();
	}
}
