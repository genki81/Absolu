package org.absolu.battle.api.constants;

import org.absolu.battle.api.pojo.Embleme;
import org.absolu.battle.api.pojo.Faction;
import org.absolu.battle.api.pojo.Guilde;

public class BattleApiConstants {

	/* API KEY + locale */
	protected static final String API_KEY = "3pz4e2c2xjn2khrk8pruxc7munv9dk5m&locale=fr_FR";

	/* URLs */
	protected static final String WOW_ROOT_URL = "https://eu.api.battle.net/wow";

	protected static final String SPEC_ICON_BASE_URL = "http://media.blizzard.com/wow/icons/36/";
	protected static final String SPEC_ICON_BASE_EXTENSION = ".jpg";
	protected static final String SPEC_ICON_NO_SPEC = "ability_skyreach_lens_flare";
	protected static final String SPEC_FOLDER = "/Absolu/images/specs/";

	protected static final String ROLE_ICON = "http://eu.battle.net/wow/static/images/icons/roles.png";

	protected static final String TABARD_ROOT_URL = "http://eu.battle.net/wow/static/images/guild/tabards/";
	protected static final String TABARD_ICON_BASE_EXTENSION = ".png";
	protected static final String TABARD_EMBLEM = "emblem_";
	protected static final String TABARD_RING = "ring-";
	protected static final String TABARD_BORDER = "border_";
	protected static final String TABARD_OVERLAY = "overlay_";
	protected static final String TABARD_BG = "bg_";
	protected static final String TABARD_SHADOW = "shadow_";
	protected static final String TABARD_HOOKS = "hooks";

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

	public static String getEmblemeBorder(Embleme e) {
		return TABARD_ROOT_URL + TABARD_BORDER + ((e.getBorder() < 10) ? "0" : "") + e.getBorder()
				+ TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeIcon(Embleme e) {
		return TABARD_ROOT_URL + TABARD_EMBLEM + e.getIcon() + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeRing(Guilde g) {
		return TABARD_ROOT_URL + TABARD_RING + Faction.findById(g.getSide()).toLowerCase() + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeRing(int id) {
		return TABARD_ROOT_URL + TABARD_RING + Faction.findById(id).toLowerCase() + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeOverlay(Embleme e) {
		return TABARD_ROOT_URL + TABARD_OVERLAY + "00" + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeBg(Embleme e) {
		return TABARD_ROOT_URL + TABARD_BG + "00" + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeShadow(Embleme e) {
		return TABARD_ROOT_URL + TABARD_SHADOW + "00" + TABARD_ICON_BASE_EXTENSION;
	}

	public static String getEmblemeHooks() {
		return TABARD_ROOT_URL + TABARD_HOOKS + TABARD_ICON_BASE_EXTENSION;
	}

	public static String buildLocalSpecIconUrl(String icon) {
		return SPEC_FOLDER + ((icon == null || icon.length() == 0) ? SPEC_ICON_NO_SPEC : icon)
				+ SPEC_ICON_BASE_EXTENSION;
	}

	public static String getRoleIcon() {
		return ROLE_ICON;
	}

}
