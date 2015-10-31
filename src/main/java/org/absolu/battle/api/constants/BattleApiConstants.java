package org.absolu.battle.api.constants;

public class BattleApiConstants {

	/* API KEY + locale */
	protected static final String API_KEY = "3pz4e2c2xjn2khrk8pruxc7munv9dk5m&locale=fr_FR";

	/* URLs */
	protected static final String SPEC_ICON_BASE_URL = "http://media.blizzard.com/wow/icons/36/";
	protected static final String SPEC_ICON_BASE_EXTENSION = ".jpg";
	protected static final String SPEC_ICON_NO_SPEC = "ability_skyreach_lens_flare";
	protected static final String WOW_ROOT_URL = "https://eu.api.battle.net/wow";

	/* Data */
	protected static final String GUILD_NAME = "Absolu";
	protected static final String REALM_NAME = "Confrérie du Thorium";

	private BattleApiConstants() {
	}

	public static String getGuildeMembersQueryUrl() {
		return WOW_ROOT_URL + "/guild/" + REALM_NAME + "/" + GUILD_NAME + "?fields=members&jsonp=absolu&apikey="
				+ API_KEY;
	}

	public static String getRacesQueryUrl() {
		return WOW_ROOT_URL + "/data/character/races?apikey=" + API_KEY;
	}

	public static String getClassesQueryUrl() {
		return WOW_ROOT_URL + "/data/character/classes?apikey=" + API_KEY;
	}

	public static String buildSpecIconUrl(final String icon) {
		return SPEC_ICON_BASE_URL + ((icon == null || icon.length() == 0) ? SPEC_ICON_NO_SPEC : icon)
				+ SPEC_ICON_BASE_EXTENSION;
	}

	public static String getCharacterQueryUrl(final String name, final String realm) {
		return WOW_ROOT_URL + "/character/" + realm + "/" + name + "?fields=items,talents&apikey=" + API_KEY;
	}
}
