import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    static String[][] SETS = { { "Dominaria", "DOM" }, { "Rivals of Ixalan", "RIX" }, { "Ixalan", "XLN" },
            { "Hour of Devastation", "HOU" }, { "Amonkhet", "AKH" }, { "Aether Revolt", "AER" }, { "Kaladesh", "KLD" },
            { "Eldritch Moon", "EMN" }, { "Shadows over Innistrad", "SOI" }, { "Oath of the Gatewatch", "OGW" },
            { "Battle for Zendikar", "BFZ" }, { "Magic Origins", "ORI" }, { "Dragons of Tarkir", "DTK" },
            { "Fate Reforged", "FRF" }, { "Khans of Tarkir", "KTK" }, { "Magic 2015", "M15" },
            { "Journey into Nyx", "JOU" }, { "Born of the Gods", "BNG" }, { "Theros", "THS" }, { "Magic 2014", "M14" },
            { "Dragon's Maze", "DGM" }, { "Gatecrash", "GTC" }, { "Return to Ravnica", "RTR" }, { "Magic 2013", "M13" },
            { "Avacyn Restored", "AVR" }, { "Dark Ascension", "DKA" }, { "Innistrad", "ISD" }, { "Magic 2012", "M12" },
            { "New Phyrexia", "NPH" }, { "Mirrodin Besieged", "MBS" }, { "Scars of Mirrodin", "SOM" },
            { "Magic 2011", "M11" }, { "Rise of the Eldrazi", "ROE" }, { "Worldwake", "WWK" }, { "Zendikar", "ZEN" },
            { "Magic 2010", "M10" }, { "Alara Reborn", "ARB" }, { "Conflux", "CFX" }, { "Shards of Alara", "ALA" },
            { "Eventide", "EVE" }, { "Shadowmoor", "SHM" }, { "Morningtide", "MT" }, { "Lorwyn", "LW" },
            { "Tenth Edition", "10E" }, { "Future Sight", "FUT" }, { "Planar Chaos", "PC" },
            { "Time Spiral \"Timeshifted\"", "TSTS" }, { "Time Spiral", "TS" }, { "Coldsnap", "CS" },
            { "Dissension", "DI" }, { "Guildpact", "GP" }, { "Ravnica: City of Guilds", "RAV" },
            { "Ninth Edition", "9E" }, { "Ninth Edition Boxed Set", "9EB" }, { "Saviors of Kamigawa", "SOK" },
            { "Betrayers of Kamigawa", "BOK" }, { "Champions of Kamigawa", "CHK" }, { "Fifth Dawn", "5DN" },
            { "Darksteel", "DS" }, { "Mirrodin", "MI" }, { "Eighth Edition", "8E" },
            { "Eighth Edition Boxed Set", "8EB" }, { "Scourge", "SC" }, { "Legions", "LE" }, { "Onslaught", "ON" },
            { "Judgment", "JU" }, { "Torment", "TR" }, { "Odyssey", "OD" }, { "Apocalypse", "AP" },
            { "Seventh Edition", "7E" }, { "Planeshift", "PS" }, { "Invasion", "IN" }, { "Prophecy", "PR" },
            { "Nemesis", "NE" }, { "Mercadian Masques", "MM" }, { "Urza's Destiny", "UD" },
            { "Classic Sixth Edition", "6E" }, { "Urza's Legacy", "UL" }, { "Urza's Saga", "US" }, { "Exodus", "EX" },
            { "Stronghold", "SH" }, { "Tempest", "TP" }, { "Weatherlight", "WL" }, { "Fifth Edition", "5E" },
            { "Visions", "VI" }, { "Mirage", "MR" }, { "Alliances", "AI" }, { "Homelands", "HL" }, { "Ice Age", "IA" },
            { "Fourth Edition", "4E" }, { "Fallen Empires", "FE" }, { "The Dark", "DK" }, { "Legends", "LG" },
            { "Revised Edition", "RV" }, { "Antiquities", "AQ" }, { "Arabian Nights", "AN" },
            { "Unlimited Edition", "UN" }, { "Limited Edition Beta", "BE" }, { "Limited Edition Alpha", "AL" },
            { "Commander 2017 Edition", "C17" }, { "Commander 2016 Edition", "C16" },
            { "Commander 2015 Edition", "C15" }, { "Commander 2014 Edition", "C14" },
            { "Commander 2013 Edition", "C13" }, { "Commander", "CMD" }, { "Planechase Anthology", "PCA" },
            { "Planechase 2012 Edition", "PC2" }, { "Planechase", "PCH" }, { "Conspiracy: Take the Crown", "CN2" },
            { "Conspiracy", "CNS" }, { "Portal Three Kingdoms", "P3K" }, { "Portal Second Age", "PO2" },
            { "Portal", "PO" }, { "Starter 1999", "ST" }, { "Media Inserts", "MBP" },
            { "Archenemy: Nicol Bolas", "E01" }, { "Archenemy", "ARC" }, { "Masters 25", "A25" },
            { "Iconic Masters", "IMA" }, { "Modern Masters 2017 Edition", "MM3" },
            { "Modern Masters 2015 Edition", "MM2" }, { "Modern Masters", "MMA" }, { "Eternal Masters", "EMA" },
            { "Vintage Masters", "VMA" }, { "Welcome Deck 2017", "W17" }, { "Welcome Deck 2016", "W16" },
            { "Duel: Merfolk vs Goblins", "DDT" }, { "Duel Decks: Mind vs. Might", "DDS" },
            { "Duel Decks: Nissa vs. Ob Nixilis", "DDR" }, { "Duel Decks: Blessed vs. Cursed", "DDQ" },
            { "Duel Decks: Zendikar vs. Eldrazi", "DDP" }, { "Duel Decks: Elspeth vs Kiora", "DDO" },
            { "Duel Decks: Speed vs. Cunning", "DDN" }, { "Duel Decks: Jace vs. Vraska", "DDM" },
            { "Duel Decks: Heroes vs. Monsters", "DDL" }, { "Duel Decks: Sorin vs. Tibalt", "DDK" },
            { "Duel Decks: Izzet vs. Golgari", "DDJ" }, { "Duel Decks: Venser vs. Koth", "DDI" },
            { "Duel Decks: Ajani vs. Nicol Bolas", "DDH" }, { "Duel Decks: Knights vs. Dragons", "DDG" },
            { "Duel Decks: Elspeth vs. Tezzeret", "DDF" }, { "Duel Decks: Phyrexia vs. the Coalition", "PVC" },
            { "Duel Decks: Garruk vs. Liliana", "GVL" }, { "Duel Decks: Divine vs. Demonic", "DVD" },
            { "Duel Decks: Jace vs. Chandra", "JVC" }, { "Duel Decks: Elves vs. Goblins", "EVG" },
            { "From the Vault: Transform", "V17" }, { "From the Vault: Lore", "V16" },
            { "From the Vault: Angels", "V15" }, { "From the Vault: Annihilation", "V14" },
            { "From the Vault: Twenty", "V13" }, { "From the Vault: Realms", "V12" },
            { "From the Vault: Legends", "FVL" }, { "From the Vault: Relics", "FVR" },
            { "From the Vault: Exiled", "FVE" }, { "From the Vault: Dragons", "FVD" },
            { "Premium Deck Series: Graveborn", "PD3" }, { "Premium Deck Series: Fire and Lightning", "PD2" },
            { "Premium Deck Series: Slivers", "PDS" }, { "MTGO Masters Edition IV", "ME4" },
            { "MTGO Masters Edition III", "ME3" }, { "MTGO Masters Edition II", "ME2" },
            { "MTGO Masters Edition", "MED" }, { "Commander Anthology", "CMA" }, { "Commander's Arsenal", "CM1" },
            { "Chronicles", "CH" }, { "Masterpiece Series: Amonkhet Invocations", "MPSAKH" },
            { "Masterpiece Series: Kaladesh Inventions", "MPSKLD" }, { "Zendikar Expeditions", "EXP" },
            { "Unstable", "UST" }, { "Unhinged", "UH" }, { "Unglued", "UG" }, { "Commander Anthology", "CMAT" } };

    static StringBuffer out;
    static HashMap<String, String> cardRule = new HashMap<>();

    static void printEntry(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            out.append("<" + desc + ">" + card.get(key) + "</" + desc + ">\n");
        }
    }

    static void printNo(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            out.append("<" + desc + ">" + card.getString(key) + "</" + desc + ">\n");
        } else if (card.has("mciNumber")) {
            out.append("<" + desc + ">" + card.getString("mciNumber") + "</" + desc + ">\n");
        }

    }

    static String printName(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            String name = null;
            if (card.has("layout")
                    && (card.getString("layout").equals("split") || card.getString("layout").equals("aftermath"))) {
                JSONArray names = card.getJSONArray("names");
                int size = names.length();
                String suffix = "(";
                for (int i = 0; i < size; i++) {
                    suffix += names.getString(i);
                    if (i < size - 1) {
                        suffix += "/";
                    }
                }
                suffix += ")";
                name = card.getString(key) + " " + suffix;
            } else {
                name = card.getString(key);
            }
            out.append("<" + desc + ">" + name + "</" + desc + ">\n");
            return name;
        }
        return null;
    }

    static String reorderMana(String mana) {
        String s = mana;
        int w = 0, u = 0, b = 0, r = 0, g = 0;
        boolean noColorMana = false;
        while (s.length() > 0) {
            switch (s.toCharArray()[s.length() - 1]) {
            case 'W':
                w++;
                break;
            case 'U':
                u++;
                break;
            case 'B':
                b++;
                break;
            case 'R':
                r++;
                break;
            case 'G':
                g++;
                break;
            default:
                noColorMana = true;
                break;
            }
            if (noColorMana) {
                break;
            } else {
                s = s.substring(0, s.length() - 1);
            }
        }
        for (int i = 0; i < w; i++) {
            s += "W";
        }
        for (int i = 0; i < u; i++) {
            s += "U";
        }
        for (int i = 0; i < b; i++) {
            s += "B";
        }
        for (int i = 0; i < r; i++) {
            s += "R";
        }
        for (int i = 0; i < g; i++) {
            s += "G";
        }
        return s;
    }

    static boolean isColorless(String mana) {
        if (mana.contains("W") || mana.contains("U") || mana.contains("B") || mana.contains("R")
                || mana.contains("G")) {
            return false;
        }
        return true;
    }

    static void printMana(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            String mana = card.getString(key);
            mana = mana.replaceAll("/P", "P").replaceAll("\\{(.)\\}", "$1").replaceAll("\\{(\\d+)\\}", "$1");
            if (!mana.contains("{")) {
                mana = reorderMana(mana);
            }
            out.append("<" + desc + ">" + mana + " (" + card.getInt("cmc") + ")</" + desc + ">\n");
        }
        if ((!card.has(key) && card.has("colors"))
                || (card.has(key) && card.has("colors") && isColorless(card.getString(key)))) {
            JSONArray colors = card.getJSONArray("colors");
            int size = colors.length();
            out.append("<ColorIndicator>");
            for (int i = 0; i < size; i++) {
                out.append(colors.getString(i));
                if (i < size - 1) {
                    out.append(" ");
                }
            }
            out.append("</ColorIndicator>\n");
        }
    }

    static String printText(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            out.append("<" + desc + ">"
                    + card.getString(key).replaceAll("/P}", "P}").replaceAll("−", "-").replaceAll(" CHAOS", " {CHAOS}")
                    + "</" + desc + ">\n");
            return card.getString(key);
        } else {
            String mana = null;
            switch (card.getString("name")) {
            case "Plains":
            case "Snow-Covered Plains":
                mana = "W";
                break;
            case "Island":
            case "Snow-Covered Island":
                mana = "U";
                break;
            case "Swamp":
            case "Snow-Covered Swamp":
                mana = "B";
                break;
            case "Mountain":
            case "Snow-Covered Mountain":
                mana = "R";
                break;
            case "Forest":
            case "Snow-Covered Forest":
                mana = "G";
                break;
            }
            if (mana != null) {
                out.append("<" + desc + ">" + "({T}: Add {" + mana + "}.)" + "</" + desc + ">\n");
            }
        }
        return "";
    }

    static void printType(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            String type = card.getString(key).replaceAll("’", "'");
            if (card.has("power")) {
                type += " " + card.get("power") + "/" + card.get("toughness");
            }
            if (card.has("loyalty")) {
                type += " (Loyalty: " + card.get("loyalty") + ")";
            }
            out.append("<" + desc + ">" + type + "</" + desc + ">\n");
        }
    }

    static void printRulings(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            JSONArray rules = card.getJSONArray(key);
            StringBuffer buffer = new StringBuffer();
            int size = rules.length();
            for (int i = 0; i < size; i++) {
                JSONObject rule = rules.getJSONObject(i);
                String date = rule.getString("date");
                buffer.append(date.replaceAll("-", "/") + ": " + rule.get("text"));
                if (i < size - 1) {
                    buffer.append("\n");
                }
            }
            String name = card.getString("name");
            String str = buffer.toString().replaceAll(name, "<" + name + ">").replaceAll(" to your mana pool", "")
                    .replaceAll("…", "...");
            out.append("<" + desc + ">" + str + "</" + desc + ">\n");
        }
    }

    static void printLegal(JSONArray legals, String key) {
        int size = legals.length();
        String str = null;
        for (int i = 0; i < size; i++) {
            JSONObject legal = legals.getJSONObject(i);
            if (legal.getString("format").equals(key)) {
                str = legal.getString("legality");
                break;
            }
        }
        if (str != null) {
            out.append("<" + key + ">" + str + "</" + key + ">\n");
        }
    }

    static void printLegals(JSONObject card, String key) {
        if (card.has(key)) {
            JSONArray legals = card.getJSONArray(key);
            printLegal(legals, "Vintage");
            printLegal(legals, "Legacy");
            printLegal(legals, "Modern");
        }
    }

    static void printRarity(JSONObject card, String key, String desc, String code, boolean special) {
        if (special || code.equals("EXP") || code.equals("MPSAKH") || code.equals("MPSAKH")) {
            out.append("<" + desc + ">" + "Special" + "</" + desc + ">\n");
        } else if (card.has(key)) {
            out.append("<" + desc + ">" + card.getString(key).replace("Basic Land", "Land") + "</" + desc + ">\n");
        }
    }

    static void printOtherPart(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            JSONArray names = card.getJSONArray(key);
            int size = names.length();
            for (int i = 0; i < size; i++) {
                String name = names.getString(i);
                if (!name.equals(card.getString("name"))) {
                    if (card.has("layout") && (card.getString("layout").equals("split")
                            || card.getString("layout").equals("aftermath"))) {
                        String suffix = "(";
                        for (int j = 0; j < size; j++) {
                            suffix += names.getString(j);
                            if (j < size - 1) {
                                suffix += "/";
                            }
                        }
                        suffix += ")";
                        name += " " + suffix;
                    }
                    out.append("<" + desc + ">" + name + "</" + desc + ">\n");
                    break;
                }
            }
        }
    }

    static void printArtist(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            out.append("<" + desc + ">" + card.getString(key).replaceAll("[“”]", "\"") + "</" + desc + ">\n");
        }
    }

    static void printWatermark(JSONObject card, String key, String desc) {
        if (card.has(key)) {
            String watermark = card.getString(key);
            if (watermark.equals("White") || watermark.equals("Blue") || watermark.equals("Black")
                    || watermark.equals("Red") || watermark.equals("Green")) {
                return;
            }
            out.append("<" + desc + ">" + card.get(key) + "</" + desc + ">\n");
        }
    }

    static void printCard(JSONObject card, String code, String set, boolean special) {
        out.append("<Card>\n");
        out.append("<SetId>" + code + "</SetId>\n");
        printNo(card, "number", "No");
        String name = printName(card, "name", "Name");
        printType(card, "type", "Type");
        printMana(card, "manaCost", "ManaCost");
        String rule = printText(card, "text", "CardText");

        if (!cardRule.containsKey(name)) {
            cardRule.put(name, rule);
        } else {
            String s = cardRule.get(name);
            if (!rule.equals(s)) {
                System.err.println(name);
                System.err.println(s);
                System.err.println("------------");
                System.err.println(rule);
                System.out.println();
            }
        }

        printEntry(card, "flavor", "Flavor");
        printArtist(card, "artist", "Artist");
        if (code.equals("RAV") || code.equals("GP") || code.equals("DI") || code.equals("SOM") || code.equals("MBS")
                || code.equals("NPH") || code.equals("RTR") || code.equals("GTC") || code.equals("DGM")
                || code.equals("KTK") || code.equals("FRF") || code.equals("DTK") || code.equals("UST")
                || code.equals("A25")) {
            printWatermark(card, "watermark", "Watermark");
        }
        printEntry(card, "multiverseid", "Multiverseid");
        printRulings(card, "rulings", "Rulings");
        if (card.has("reserved") && card.getBoolean("reserved")) {
            out.append("<Reserved>This card is on the reserved list</Reserved>\n");
        }
        printLegals(card, "legalities");
        out.append("<Set>" + set + "</Set>\n");
        printRarity(card, "rarity", "Rarity", code, special);
        printOtherPart(card, "names", "OtherPart");
        out.append("</Card>\n");
    }

    static Vector<JSONObject> getReordered(JSONArray array, String code, String special) {
        File file = new File("C:\\Users\\LU_cifer\\workspace\\JniLua\\Oracle\\MtgOracle_"
                + (special == null ? code : special) + ".txt");
        if (!file.exists()) {
            return null;
        }

        Vector<JSONObject> all = new Vector<>();
        for (int i = 0; i < array.length(); i++) {
            all.add(array.getJSONObject(i));
        }

        Vector<String> names = new Vector<>();
        Vector<Integer> ids = new Vector<>();
        BufferedReader reader = null;
        try {
            String line = null;
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("<Name>")) {
                    String name = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
                    if (name.contains(" (")) {
                        name = name.substring(0, name.indexOf(" ("));
                    }
                    names.add(name);
                }
                if (line.startsWith("<Multiverseid>")) {
                    String id = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
                    ids.add(Integer.parseInt(id));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        Vector<JSONObject> ret = new Vector<>();
        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            String name = names.get(i);
            boolean found = false;
            for (JSONObject card : all) {
                if (card.has("multiverseid") && id == card.getInt("multiverseid")) {
                    ret.add(card);
                    all.remove(card);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.err.println(name);
            }
        }
        return ret;
    }

    static boolean REORDER = true;

    static void printSet(JSONObject all, String code, String special) {
        out = new StringBuffer();

        JSONObject set = all.getJSONObject(code);
        String name = set.getString("name");

        System.out.println(code + " : " + name);

        if (name.startsWith("Commander 201") && !name.endsWith("Edition")) {
            name += " Edition";
        }

        for (String[] s : SETS) {
            if (name.equals(s[0])) {
                code = s[1];
                break;
            }
        }

        if (name.startsWith("Magic: The Gathering")) {
            name = name.substring("Magic: The Gathering".length() + 1);
        } else if (name.endsWith(" Core Set")) {
            name = name.replace(" Core Set", "");
        } else if (name.startsWith("Masters Edition")) {
            name = "MTGO " + name;
        } else if (name.startsWith("Masterpiece Series: ")) {
            name = name.replace("Masterpiece Series: ", "");
        } else if (name.contains("the Coalition")) {
            name = name.replace("the", "The");
        } else if (name.contains(" (2014)")) {
            name = name.replace(" (2014)", "");
        }

        HashMap<String, Vector<JSONObject>> map = new HashMap<>();

        JSONArray cards = set.getJSONArray("cards");
        int size = cards.length();

        if (REORDER) {
            Vector<JSONObject> vector = getReordered(cards, code, special);
            if (vector == null) {
                return;
            }
            for (JSONObject card : vector) {
                printCard(card, code, name, special != null);
                out.append("\n");
            }
        } else {
            Vector<String> vector = new Vector<>();

            for (int i = 0; i < size; i++) {
                JSONObject card = cards.getJSONObject(i);
                if (special != null) {
                    if (!card.has("layout")
                            || !(card.getString("layout").equals("plane") || card.getString("layout").equals("scheme")
                                    || card.getString("layout").equals("phenomenon"))) {
                        continue;
                    }
                } else if (card.has("layout") && (card.getString("layout").equals("token")
                        || card.getString("layout").equals("plane") || card.getString("layout").equals("scheme")
                        || card.getString("layout").equals("phenomenon"))) {
                    continue;
                }

                String num = "";
                if (card.has("number")) {
                    num = card.getString("number");
                } else if (card.has("mciNumber")) {
                    num = card.getString("mciNumber");
                }
                if (num.isEmpty()) {
                    num = "999";
                }
                if (map.containsKey(num)) {
                    map.get(num).add(card);
                } else {
                    Vector<JSONObject> v = new Vector<>();
                    v.add(card);
                    map.put(num, v);
                }
                // printCard(card, code, name);
                // out.append("\n");
            }

            for (String s : map.keySet()) {
                vector.add(s);
            }
            Collections.sort(vector, new Comparator<String>() {

                @Override
                public int compare(String arg0, String arg1) {
                    String n0 = arg0;
                    String n1 = arg1;
                    if (arg0.startsWith("S") || arg0.startsWith("P") || arg0.startsWith("★")) {
                        n0 = arg0.substring(1, arg0.length());
                    }
                    if (arg1.startsWith("S") || arg1.startsWith("P") || arg1.startsWith("★")) {
                        n1 = arg1.substring(1, arg1.length());
                    }
                    if (arg0.endsWith("a") || arg0.endsWith("b") || arg0.endsWith("c") || arg0.endsWith("d")
                            || arg0.endsWith("e")) {
                        n0 = arg0.substring(0, arg0.length() - 1);
                    }
                    if (arg1.endsWith("a") || arg1.endsWith("b") || arg1.endsWith("c") || arg1.endsWith("d")
                            || arg1.endsWith("e")) {
                        n1 = arg1.substring(0, arg1.length() - 1);
                    }
                    if (n0.equals(n1)) {
                        return arg0.compareTo(arg1);
                    } else {
                        return Integer.parseInt(n0) - Integer.parseInt(n1);
                    }
                }
            });

            for (String s : vector) {
                for (JSONObject card : map.get(s)) {
                    printCard(card, code, name, special != null);
                    out.append("\n");
                }
            }
        }

        File file = new File("Oracle/MtgOracle_" + (special == null ? code : special) + ".txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(out.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("AllSets-x.json");
        BufferedReader reader = null;
        StringBuffer str = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        JSONObject all = new JSONObject(str.toString());
        Iterator<String> it = all.keys();
        while (it.hasNext()) {
            String code = it.next();
            printSet(all, code, null);
        }
        printSet(all, "HOP", "Plane");
        printSet(all, "PC2", "Plane2012");
        printSet(all, "ARC", "Scheme");
        printSet(all, "E01", "SchemeNicolBolas");
    }

}
