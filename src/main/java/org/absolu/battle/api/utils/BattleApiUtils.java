package org.absolu.battle.api.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.absolu.battle.api.constants.BattleApiConstants;
import org.absolu.battle.api.pojo.Classe;
import org.absolu.battle.api.pojo.Guilde;
import org.absolu.battle.api.pojo.Personnage;
import org.absolu.battle.api.pojo.Race;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class BattleApiUtils {
	private final static Logger LOGGER = LogManager.getLogger(BattleApiUtils.class);

	private static List<Race> races;
	private static List<Classe> classes;
	static {
		races = getListRaces();
		classes = getListClasses();
	}

	private BattleApiUtils() {
	}

	public static Guilde getGuilde() {
		String readGuildMembersCount = queryBattle(BattleApiConstants.getGuildeMembersQueryUrl());
		LOGGER.debug(readGuildMembersCount);
		if (readGuildMembersCount == null) {
			return null;
		}
		readGuildMembersCount = readGuildMembersCount.substring(readGuildMembersCount.indexOf("(") + 1);
		readGuildMembersCount = readGuildMembersCount.substring(0, readGuildMembersCount.length() - 2);
		Guilde g = new Guilde();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(Guilde.class);
			g = reader.readValue(readGuildMembersCount);

			LOGGER.info("Nombre de membres " + g.getMembers().size());
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return g;
	}

	public static Personnage getPersonnage(final String name, final String realm) {
		String readCharacter = queryBattle(BattleApiConstants.getCharacterQueryUrl(name, realm));
		LOGGER.debug(readCharacter);
		if (readCharacter == null) {
			return null;
		}
		Personnage p = new Personnage();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(Personnage.class);
			p = reader.readValue(readCharacter);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return p;
	}

	private static List<Race> getListRaces() {
		String sRaces = queryBattle(BattleApiConstants.getRacesQueryUrl());
		LOGGER.debug(sRaces);
		if (sRaces == null) {
			return null;
		}
		List<Race> races = new ArrayList<Race>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Race>>() {
			}).withRootName("races");
			races = reader.readValue(sRaces);
			LOGGER.debug("Nombre de races " + races.size());
			for (Race race : races) {
				LOGGER.debug(race.toString());
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return races;
	}

	private static List<Classe> getListClasses() {
		String sClasses = queryBattle(BattleApiConstants.getClassesQueryUrl());
		LOGGER.debug(sClasses);
		if (sClasses == null) {
			return null;
		}
		List<Classe> classes = new ArrayList<Classe>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectReader reader = mapper.readerFor(new TypeReference<List<Classe>>() {
			}).withRootName("classes");
			classes = reader.readValue(sClasses);
			LOGGER.debug("Nombre de races " + classes.size());
			for (Classe classe : classes) {
				LOGGER.debug(classe.toString());
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return classes;
	}

	private static String queryBattle(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = HttpClientBuilder.create().build();
		try {
			HttpGet httpGet = new HttpGet(URIUtil.encodeQuery(url));
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
				LOGGER.error("Failed to download file");
				return null;
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return builder.toString();
	}

	public static Classe getClasseById(int id) {
		for (Classe classe : classes) {
			if (classe.getId() == id) {
				return classe;
			}
		}
		return new Classe();
	}

	private static void saveImage(final String root, final String embleme) {
		OutputStream fos = null;
		InputStream is = null;
		try {
			File rootDir = new File(root);
			if (!rootDir.exists()) {
				rootDir.mkdirs();
			}
			File fOut = new File(root, embleme);
			if (fOut.exists()) {
				fOut.delete();
			}
			fOut.createNewFile();
			fos = new FileOutputStream(fOut);
			URL icon = new URL(BattleApiConstants.getEmblemeRootUrl() + embleme);
			is = icon.openStream();

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				fos.write(b, 0, length);
			}

		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}

	}

	public static void handleEmblem(final String root, Guilde g) {
		saveImage(root + "/images/", BattleApiConstants.getEmblemeRing(g));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeIcon(g.getEmblem()));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeBorder(g.getEmblem()));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeBg(g.getEmblem()));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeOverlay(g.getEmblem()));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeShadow(g.getEmblem()));
		saveImage(root + "/images/", BattleApiConstants.getEmblemeHooks());

	}

	public static List<Race> getRaces() {
		return races;
	}

	public static List<Classe> getClasses() {
		return classes;
	}

	private static Color getColorFromHex(final String hexColor) {
		return new Color(Integer.valueOf(hexColor.substring(2, 4), 16), Integer.valueOf(hexColor.substring(4, 6), 16),
				Integer.valueOf(hexColor.substring(6, 8), 16), Integer.valueOf(hexColor.substring(0, 2), 16));
	}

	public static String getColorListFromHex(final String hexColor) {
		Color color = getColorFromHex(hexColor);
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(color.getRed()).append(",");
		sb.append(color.getGreen()).append(",");
		sb.append(color.getBlue());
		sb.append("]");
		return sb.toString();

	}
}
