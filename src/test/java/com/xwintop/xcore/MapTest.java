package com.xwintop.xcore;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import com.xwintop.xcore.util.StrUtil;

public class MapTest {
	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<>();
		map.put(null, "a");
		map.put(null, "b");
		map.put("a", "b");
		map.put("a", null);
		map.put(null, null);
		System.out.println(map.size());
		System.out.println(map);

		Map<String, String> mapTable = new Hashtable<>();
		mapTable.put(null, "a");
		mapTable.put(null, "b");
		mapTable.put("a", "b");
		mapTable.put("a", null);
		mapTable.put(null, null);
		System.out.println(mapTable.size());
		System.out.println(mapTable);
	}

	@Test
	public void testString() {
		String[] name = new String[] { "LEVANT", "MILD TUNE", "MILD WALTZ", "CSCL OSAKA", "EBBA MAERSK",
				"TRAMMO STANTON", "MILD TEMPO", "EAGLE SKY", "MSC CRISTIANA      ", "MILD JAZZ          ",
				"CATHRINE RICKMERS  ", "MILD SONATA        ", "CHUN JIN           ", "TOP BRILLIANCE     ",
				"JJ STAR            ", "CSCL JUPITER       ", "DANU BHUM          ", "DONG FANG FU       ",
				"CSCL MONTEVIDEO    ", "HAN HE             ", "CK GLORY           ", "YM IMMENSE         ",
				"KRASZEWSKI         ", "LE RONG            ", "HYUNDAI VANCOUVER  ", "HYUNDAI INTEGRAL   ",
				"CAP ARNAUTI        ", "UNI FLORIDA        ", "SITC NAGOYA        ", "STAR MINERVA       ",
				"MATAQUITO          ", "OOCL SHANGHAI      ", "KUO CHANG          ", "MIAMI TRADER       ",
				"KOTA LANGSAR       ", "OOCL BRUSSELS      ", "OOCL VANCOUVER     ", "BALEARES           ",
				"SITC KWANGYANG     ", "SHANGHAI SUPER EXPR", "XIANG RUI MEN      ", "DIVINE ACE         ",
				"CHIPOL TAIHU       ", "SINOTRANS SHANGHAI ", "STAR UNIX          ", "NYK ATHENA         ",
				"WAN HAI 507        ", "NORTHERN DIAMOND   ", "XIN LOS ANGELES    ", "ALTAIR             ",
				"MAPLE HARMONY      ", "SINOTRANS HONG KONG", "WAN HAI 202        ", "CMA CGM TARPON     ",
				"CSCL EAST CHINA SEA", "SKY LOVE           ", "JI XIANG SONG      ", "PHILIPPOS-MICHALIS ",
				"TRIDENT            ", "MAERSK CONAKRY     ", "BAO FLOURISH       ", "HOEGH BERLIN       ",
				"LIGULAO            ", "BROOKLYN BRIDGE    ", "CALA PINGUINO      ", "DONG FANG QIANG    ",
				"YM PLUM            ", "OOCL ISTANBUL      ", "KOTA LAMBANG       ", "CMA CGM GEORG FORST",
				"BOW SAILOR         ", "TALLAHASSEE        ", "MOL DESTINY        ", "PINE 2             ",
				"CSCL NEPTUNE       ", "TRINIDAD TRADER    ", "UNDARUM            ", "ARABIAN EXPRESS    ",
				"SITC OSAKA         ", "MSC PALOMA         ", "MSC BEATRICE       ", "HYUNDAI SPEED      ",
				"ARTEMIS            ", "SINOTRANS QINGDAO  ", "SAN CLEMENTE       ", "COSCO KIKU         ",
				"GLORY FORTUNE      ", "EASTERN EXPRESS    ", "MAERSK WIESBADEN   ", "YM ETERNITY        ",
				"DA TONG YUN        ", "CMA CGM URAL       ", "ZIM ISTANBUL       ", "HE YANG            ",
				"NYK LYNX           ", "CSCL NAGOYA        ", "GUNHILDE MAERSK    ", "ZIM LIVORNO        ",
				"RHL AUDACIA        ", "MOL NALA           ", "HANJIN HAMBURG     ", "HANJIN LOUISIANA   ",
				"MAERSK VIRGINIA    ", "SITC HEBEI         ", "URU BHUM           ", "SILVER PEACE       ",
				"ZHONG TAI 8        ", "HYUNDAI FAITH      ", "UASC UMM QASR      ", "PAPUAN CHIEF       ",
				"APL AGATE          ", "EVER UBERTY        ", "LANTAU BAY         ", "LARENTIA           ",
				"FESCO TRADER       ", "SKOMVAER           ", "SITC SHANDONG      ", "CPO NORFOLK        ",
				"ANNA MAERSK        ", "NAVARRA            ", "LE TONG            ", "PUELCHE            ",
				"TEMPANOS           ", "MAUNALEI           ", "KSL ANYANG         ", "MSC SHANNON        ",
				"MSC BRUXELLES      ", "CSCL AUTUMN        ", "TS SINGAPORE       ", "HAI FENG LIAN XING ",
				"CSC REN HAI        ", "KOTA LAJU          ", "BAY BRIDGE         ", "APL PHOENIX        ",
				"DONGJIN VENUS      ", "SIRI BHUM          ", "SAN PEDRO          ", "XUAN JING          ",
				"ZARDIS             ", "STAR ZETA          ", "AKARI              ", "TORTUGAS           ",
				"RUBINA SCHULTE     ", "DS KINGDOM         ", "MAERSK LAGUNA      ", "APL TOURMALINE     ",
				"SINOTRANS BEIJING  ", "MATAR N            ", "EVER DIVINE        ", "GUO TOU 110        ",
				"JJ TOKYO           ", "XIN NING BO        ", "SITC TOKYO         ", "MSC TRIESTE        ",
				"HYUNDAI STRIDE     ", "HYUNDAI NEW YORK   ", "ALTAIR SKY         ", "HOEGH CHENNAI      ",
				"HAMMONIA PESCARA   ", "XIN BEIJING        ", "EVER EAGLE         ", "WAN HAI 262        ",
				"CMA CGM FIGARO     ", "CSCL TOKYO         ", "CAPE MONTEREY      ", "CLIFFORD MAERSK    ",
				"HYUNDAI BRIDGE     ", "WENDE              ", "NORTHERN VALENCE   ", "THANLWIN STAR      ",
				"MORNING COMPASS    ", "NEW LUCKY V        ", "MSC LA SPEZIA      ", "CSCL MANZANILLO    ",
				"JRS CARINA         ", "OOCL DUBAI         ", "SARA               ", "AMBASSADOR BRIDGE  ",
				"HANJIN GENEVA      ", "QIU JIN            " };
		StringBuffer stringBuffer = new StringBuffer(); 
		String[] lrimoShipNoS = new String[]{"9686388", "9704594", "9666869", "9383522", "9321524", "9762871", "9704582",
                "9161819", "9453298", "9666871", "9236523", "9666857", "9113173", "9182162", "9324966", "9467263", "9112698", "9162423", "9385984", "9240055", "9284348", "9319131", "9432153", "9183740", "9463085",
                "9347592", "9629380", "9308039", "9308053", "9355513", "9400095", "9198111", "9172313", "9251846", "9439735", "9622590", "9306990", "9506394", "9610535", "9033751", "8712491", "9610432", "97341743",
                "9633745", "9365166", "9247766", "9326407", "9405033", "9307217", "9362827", "9545572", "9330769", "8901755", "9331012", "9645918", "9158862", "9608922", "9706279", "9367970", "9525285", "9609835",
                "9295842", "9561605", "9458999", "9377705", "9122538", "9198264", "9389693", "9351036", "9702144", "9164720", "9611046", "9352418", "9438353", "9467316", "9675808", "9430090", "9148532", "9638329",
                "9441001", "9399014", "9475698", "9339595", "9631632", "9699189", "9247871", "9332729", "9299331", "9410272", "9353292", "9451343", "9705079", "9456989", "9179476", "9229324", "9383534", "9359026",
                "9318175", "9357846", "9626053", "9461491", "9631137", "9235531", "9712371", "9293234", "9607655", "9419060", "9347554", "9699115", "9689964", "9139713", "9168831", "9383596", "9312432", "9168233",
                "9490765", "9691113", "9440813", "9260421", "9548823", "9175444", "9306287", "9447897", "9273686", "9696864", "8913423", "9290567", "9645891", "9360697", "9258715", "9628685", "9340752", "9463267",
                "9597501", "9615339", "9491719", "9625281", "9548029", "9349679", "9284477", "9202900", "9319765", "9315836", "9215828", "9526942", "9082336", "9367920", "9509176", "9134282", "9677337", "9102526",
                "9270464", "9258727", "9484479", "9149835", "9385025", "9629419", "8507652", "9401051", "9314246", "9241310", "9230218", "9450600", "9383510", "9676199", "9198575", "9158587", "9336282", "9304693",
                "9112806", "9441611", "9273181", "9461403", "9402639", "9339088", "9307023", "9322243", "9409027", "9215646", "9102538"};
		for(String s:lrimoShipNoS){
			 stringBuffer.append("\"");
			 stringBuffer.append(s.trim());
			 stringBuffer.append("\",");
		 }
		System.out.println(stringBuffer.toString());
	}
	
	@Test
	public void testField() {
		System.out.println((-5)*(-5));
		//		getField(Object.class);
	}

	public void getField(Class<?> xclass) {
		Field[] fields = FieldUtils.getAllFields(xclass);
		StringBuffer stringBuffer = new StringBuffer();
		for (Field field : fields) {
			stringBuffer.append("pos.set" + StrUtil.fristToUpCase(field.getName()));
			stringBuffer.append("(ParseUtils.getDoubleFromString(" + field.getName() + "));\n");
		}
		System.out.println(stringBuffer.toString());
	}
	
	public void getField2(Class<?> xclass) {
		Field[] fields = FieldUtils.getAllFields(xclass);
		StringBuffer stringBuffer = new StringBuffer();
		int i = -1;
		for (Field field : fields) {
			stringBuffer.append("stmt.setString(" +i+",ihsCombinedPositionsData.get"+ StrUtil.fristToUpCase(field.getName()));
			stringBuffer.append("());\n");
			i++;
		}
		System.out.println(stringBuffer.toString());
	}
}
