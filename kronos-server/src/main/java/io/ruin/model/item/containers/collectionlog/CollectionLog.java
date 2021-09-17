package io.ruin.model.item.containers.collectionlog;

import io.ruin.cache.ItemDef;
//import io.ruin.model.achievements.Achievements;
//import io.ruin.model.achievements.AchievementType;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.DefaultAction;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.ItemContainerG;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.attributes.AttributeExtensions;

import java.util.Map;


public class CollectionLog extends ItemContainerG<CollectionLogItem> {

    /**
     * Variables.
     */

    /* collection log interface info */
    public static int COLLECTION_LOG_ITEM_CONTAINER = 620;
    public static int COLLECTION_LOG_ID = 621;

    /* collection log item id */
    public static int COLLECTION_LOG_ITEM_ID = 22711;

    /* collection log client script tab info int params */
    public static int[] bossParams = {40697866, 40697867, 40697868, 40697869};
    public static int[] raidParams = {40697870, 40697871, 40697872, 40697877};
    public static int[] clueParams = {40697878, 40697886, 40697887, 40697879};
    public static int[] minigameParams = {40697880, 40697881, 40697890, 40697882};
    public static int[] otherParams = {40697883, 40697888, 40697889, 40697884};

    /* collection log structures */
    public static int BOSSES_STRUCT = 471; public static int RAIDS_STRUCT = 472; public static int CLUES_STRUCT = 473;
    public static int MINIGAMES_STRUCT = 474; public static int OTHER_STRUCT = 475;

    /* collection log child ids */
    public static int BOSS_CHILD_ID = 11; public static int RAIDS_CHILD_ID = 15;
    public static int CLUES_CHILD_ID = 30; public static int MINIGAMES_CHILD_ID = 25;
    public static int OTHER_CHILD_ID = 32;

    public static final int BLANK_ID = -1, FILLER_ID = 20594;

    public static int[] COLLECTION_LOG_ITEMS = new int[]{ 13262, 13273, 7979, 13274, 13275, 13276, 13277, 13265, 4151,
            4732, 4708, 4716, 4724, 4745, 4753, 4736, 4712, 4720, 4728, 4749, 4757, 4738, 4717, 4722, 4730, 4751, 4759, 4734, 4710, 4718, 4726, 4727, 4755, 4740,
            22372, 13178, 12603, 11920, 7158, 13227, 13229, 13231, 13245, 13233, 13249, 11995, 11928, 11931, 12651, 11785, 11814, 11838, 13256, 11818,
            11820, 11822, 12816, 12819, 12823, 12827, 12833, 12829, 11929, 11932, 11990, 12644, 12643, 12645, 6737, 6733, 6731, 6735, 6739, 6724, 6562, 12650, 11832, 11834, 11836, 11812,
            11818, 11820, 11822, 12646, 7418, 7416, 21748, 21730, 21736, 21739, 21742, 21745, 21726, 12647, 7981, 12885, 7158, 3140, 12653, 7980, 11286, 12655, 12004, 11905, 12007, 12649,
            11826, 11828, 11830, 11810, 11818, 11820, 11822, 12652, 11791, 11824, 11787, 11816, 20756, 13181, 11930, 11933, 21273, 19701, 21275, 19685, 6571, 19677,
            12648, 12002, 11998, 3140, 21291, 21295, 13225, 6570, 13177, 12605, 13179, 12601, 21992, 21907, 22006, 22106, 22111, 20693, 20716, 20718, 20704, 20708,
            20706, 20710, 20712, 20720, 12921, 13200, 13201, 12936, 12932, 12927, 12922, 12938, 12934, 20851, 22386, 20997, 21003, 21043, 13652, 21018, 21021, 21024, 21015, 21034,
            21079, 21012, 21000, 21047, 21027, 6573, 24670, 22388, 22390, 22392, 22394, 22396, 22473, 22486, 22324, 22481, 22326, 22327, 22328, 22477, 22446, 22494, 22496,
            22498, 22500, 22502, 20211, 20217, 20214, 23351, 20205, 20208, 20166, 2587, 2583, 2585, 3472, 2589, 2595, 2591, 2593, 3473, 2597, 7332, 7338, 7344, 7350, 7356,
            10306, 10308, 10310, 10312, 10314, 23366, 23369, 23372, 23378, 20193, 20184, 20187, 20190, 20196, 20178, 20169, 20172, 20175, 20181, 12225, 12227, 12229, 12233, 12231,
            12235, 12237, 12239, 12241, 12243, 12215, 12217, 12219, 12223, 12221, 12205, 12207, 12209, 12213, 12211, 7362, 7366, 7364, 7368, 23381, 23384, 7394, 7390, 7386, 7396,
            7392, 7388, 12453, 12449, 12445, 12455, 12451, 12447, 20199, 20202, 10458, 10464, 10462, 10460, 10468, 12193, 12195, 12253, 12255, 12265, 12267, 10316, 10320,
            10318, 10322, 10324, 2631, 2633, 2637, 12247, 10392, 12245, 12249, 12251, 10398, 10394, 10396, 12375, 23363, 10404, 10424, 10406, 10426, 10412, 10432, 10414, 10434, 10408,
            10428, 10410, 10430, 10366, 23354, 12297, 23360, 23357, 10280, 2577, 2579, 12598, 23413, 23389, 2605, 2599, 2601, 3474, 2603, 2613, 2607, 2609, 3475, 2611, 7334, 7340, 7346, 7352, 7358, 10296, 10298,
            10300, 10302, 10304, 23392, 23398, 23401, 23404, 12283, 12277, 12279, 12285, 12281, 12293, 12287, 12289, 12295, 12291, 7370, 7372, 7378, 7380, 10452, 10446, 10454, 10448,
            10456, 10450, 12203, 12197, 12201, 12199, 12259, 12261, 12257, 12263, 12271, 12273, 12269, 12275, 7319, 7323, 7321, 7327, 7325, 12309, 12311, 12313, 2645, 2647, 2649,
            12299, 12301, 12305, 12307, 12303, 12319, 20240, 20243, 12377, 20251, 20260, 20254, 20263, 20257, 20272, 20266, 20269, 12361, 12428, 12359, 20247, 23407, 23410, 10416, 10436,
            10418, 10438, 10400, 10420, 10402, 10422, 12315, 12339, 12317, 12341, 12347, 12343, 12345, 20275, 10364, 10282, 10334, 10330, 10332, 10336, 10338, 10340, 10342, 10344, 23242, 10346, 10348, 10350,
            10352, 3481, 3483, 3485, 3486, 3488, 20146, 20149, 20152, 20155, 20158, 20161, 2581, 22231, 23227, 23232, 23237, 2627, 2623, 2625, 3477, 2629, 2619, 2615, 2617, 3476, 2621, 2657, 2653, 2655, 3478, 2659, 2673, 2669,
            2671, 3480, 2675, 2665, 2661, 2663, 3479, 2667, 12466, 12460, 12462, 12464, 12468, 12476, 12470, 12472, 12474, 12478, 12486, 12480, 12482, 12484, 7336, 7342, 7348, 7360, 10286, 10288, 10290, 10292, 10294, 23209, 23212, 23215, 23218, 23221, 10390,
            10386, 10388, 10384, 19933, 23191, 10382, 10378, 10380, 10376, 19927, 23188, 10374, 10370, 10372, 10368, 19936, 23194, 12504, 12500, 12502, 12498, 19924, 23203, 12512, 12508, 12510, 12506, 19930, 23200, 12496, 12492, 12494, 12490, 11921, 23197,
            12331, 12333, 12327, 12329, 7376, 7384, 7374, 7382, 7400, 7399, 7398, 10470, 10440, 10472, 10442, 10474, 10444, 19912, 19915, 2651, 12323, 12321, 12325, 2639, 2641, 12516, 12514, 23224, 12518, 12520, 12522, 12524, 19918, 23206, 10354, 10284,
            12426, 12422, 12437, 12424, 10334, 10330, 10332, 10336, 10338, 10340, 10342, 10344, 23242, 10346, 10348, 10350, 10352, 23185, 12389, 12391, 3481, 3483, 3485, 3486, 3488, 20146, 20149, 20152, 20155, 20158, 20161, 23258, 23261, 23264, 23267, 23276, 23282, 12526, 12534, 12536,
            12532, 12538, 20002, 1250, 12528, 19997, 19994, 12596, 23249, 12381, 12383, 12385, 12387, 12397, 12439, 12393, 12395, 12351, 12441, 12443, 19958, 19964, 19967, 19961, 19970, 19973, 19979, 19982, 19976, 19985,
            19943, 19946, 19952, 19955, 19949, 12363, 12365, 12367, 12369, 23270, 23273, 12371, 20005, 12357, 12373, 12335, 19991, 19988, 12540, 12430, 12355, 12432, 12353, 12337, 23246, 23252, 23255,
            19730, 20014, 20011, 12426, 12422, 12437, 12424, 10334, 10330, 10332, 10336, 10338, 10340, 10342, 10344, 23242, 10346, 10348, 10350, 10352, 23339, 23336, 23342, 23345, 23185, 12389,
            12391, 3481, 3483, 3485, 3486, 3488, 20146, 20152, 20155, 20158, 20161, 23258, 23261, 23264, 23267, 23276, 23279, 23282, 20059, 20017, 20068, 20071, 20074, 20077, 20065, 20062, 22246, 20143, 22239, 22236, 23348,
            20128, 20131, 20137, 20134, 20140, 20035, 20038, 20044, 20047, 20041, 20095, 20098, 20101, 20107, 20104, 20080, 20092, 20086, 20089, 20083, 20125, 20116, 20113, 20122, 20119, 20020, 20023, 20026, 20032, 20029, 19724,
            20110, 20056, 20050, 20053, 20008, 3827, 3831, 3835, 12613, 12617, 12621, 3828
            //left off on enum id 2146 val8
    };

    /* opens the collection log */
    public void openCollectionLog(Player player) {

        //Achievements.Achievement.increase(player, AchievementType._2, 1);

        /* send collection log item container */
        player.getPacketSender().sendItems(-1, -1, 620, player.getCollectionLog().getItems());

        /* collection log interface */
        player.openInterface(InterfaceType.MAIN, COLLECTION_LOG_ID);

        /* bosses */
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 10, 0, 35, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 11, 0, 35, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 12, 0, 35, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 29, 0, 35, 2);

        /* raids */
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 14, 0, 3, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 15, 0, 3, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 16, 0, 3, 2);

        /* clues */
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 22, 0, 6, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 30, 0, 6, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 31, 0, 6, 2);

        /* minigames */
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 24, 0, 10, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 25, 0, 10, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 34, 0, 10, 2);

        /* other */
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 27, 0, 18, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 32, 0, 18, 2);
        player.getPacketSender().sendAccessMask(COLLECTION_LOG_ID, 33, 0, 18, 2);

        /* client scripts */
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 0);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697866, 40697867, 40697868, 40697869, player.collection_log_current_struct = 471, player.collection_log_current_entry = 0);//BOSSES_STRUCT, 0

        sendAll = true;

    }

    /**
     * info about the collection log.
     */

    /* client script info - id: 2389, params: category value 0-4.*/
    /* values: 0=bosses, 1=raids, 2=clues, 3=minigames, 4=other */
    /* client script info - id: 2730, params: hashedId int params, structureId 471-475, slotId. */
    /* structureIds: 471=bosses, 472=raids, 473=clues, 474=minigames, 475=other */


    /* struct param 682, string. main category name. bosses, raids, clues, minigames, other. */
    /* struct param 683, enum id. enum id stores struct id info for selected item categories.*/
    /* struct param 683, int id. main category int value 0-4. */

    /* struct param 689, string name. the name for the item category. tob, xeric, easy trails, barrows, etc.*/
    /* struct param 690, id. id = enum id that stores that item categories*/


    /* sending the bosses tab */
    public static void sendBossesTab(Player player) {
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 0);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697866, 40697867, 40697868, 40697869, player.collection_log_current_struct = 471, player.collection_log_current_entry = 0);
    }

    /* sending the raids tab */
    public static void sendRaidsTab(Player player) {
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 1);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697870, 40697871, 40697872, 40697877, player.collection_log_current_struct = 472, player.collection_log_current_entry = 0);
    }

    /* sending the clues tab */
    public static void sendCluesTab(Player player) {
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 2);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697878, 40697886, 40697887, 40697879, player.collection_log_current_struct = 473, player.collection_log_current_entry = 0);
    }

    /* sending the minigames tab */
    public static void sendMinigamesTab(Player player) {
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 3);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697880, 40697881, 40697890, 40697882, player.collection_log_current_struct = 474, player.collection_log_current_entry = 0);
    }

    /* sending the other tab */
    public static void sendOtherTab(Player player) {
        player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 4);
        player.getPacketSender().sendClientScript(2730, "iiiiii", 40697883, 40697888, 40697889, 40697884, player.collection_log_current_struct = 475, player.collection_log_current_entry = 0);
    }

    /* sending specific category tab */
    public static void sendCategoryTab(Player player, int structId, int categoryId, int subCategoryId, int[] params) {
        player.getPacketSender().sendClientScript(2389, "i", categoryId);
        player.getPacketSender().sendClientScript(2730, "iii", params.length, structId, subCategoryId);
    }

    public void handleLogTab(Player player, int childId, int tab) {
        tab = player.collection_log_current_tab;
        switch (childId) {
            case 4://bosses
                player.getPacketSender().sendClientScript(2389, "i", tab);
                player.getCollectionLog().handleLogStructure(player, player.collection_log_current_struct = 471);
                return;
            case 5://raids
                player.getPacketSender().sendClientScript(2389, "i", tab);
                player.getCollectionLog().handleLogStructure(player, player.collection_log_current_struct = 472);
                return;
            case 6://clues
                player.getPacketSender().sendClientScript(2389, "i", tab);
                player.getCollectionLog().handleLogStructure(player, player.collection_log_current_struct = 473);
                return;
            case 7://minigames
                player.getPacketSender().sendClientScript(2389, "i", tab);
                player.getCollectionLog().handleLogStructure(player, player.collection_log_current_struct = 474);
                return;
            case 8://other
                player.getPacketSender().sendClientScript(2389,  "i",tab);
                player.getCollectionLog().handleLogStructure(player, player.collection_log_current_struct = 475);
                return;
        }
    }

    public void handleLogStructure(Player player, int struct) {
        struct = player.collection_log_current_struct;
        switch (struct) {
            case 471://bosses
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697866, 40697867, 40697868, 40697869, struct, player.collection_log_current_entry);
                return;
            case 472://raids
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697870, 40697871, 40697872, 40697877, struct, player.collection_log_current_entry);
                return;
            case 473://clues
                player.getPacketSender().sendClientScript(2730,  "iiiiii",40697878, 40697886, 40697887, 40697879, struct, player.collection_log_current_entry);
                return;
            case 474://minigames
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697880, 40697881, 40697890, 40697882, struct, player.collection_log_current_entry);
                return;
            case 475://other
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697883, 40697888, 40697889, 40697884, struct, player.collection_log_current_entry);
                return;

        }
    }

    public void handleKillCountSlot(Player player, CollectionLogInfo info, int slot) {
        player.collection_log_current_entry = slot;
        Config.COLLECTION_LOG_KC_TAB.set(player, player.getCollectionLog().getCollectionLogKillCount(player, info, slot));
    }

    /* gets the kill count for the collection log entries */
    public int getCollectionLogKillCount(Player player, CollectionLogInfo info, int entry) {
        entry = player.getCollection_log_current_entry();//final int
        switch (info) {
            case BOSS:
                switch (entry) {
                    case 0://abyssal sire
                        return player.abyssalSireKills.getKills();
                    case 1://alchemical hydra
                        return player.alchemicalHydraKills.getKills();
                    case 2://barrows
                        return player.barrowsChestsLooted.getKills();
                    case 3://bryophyta
                        return player.bryophytaKills.getKills();
                    case 4://callisto
                        return player.callistoKills.getKills();
                    case 5://cerberus
                        return player.cerberusKills.getKills();
                    case 6://chaos ele
                        return player.chaosElementalKills.getKills();
                    case 7://chaos fanatic
                        return player.chaosFanaticKills.getKills();
                    case 8://commander zilyana
                        return player.commanderZilyanaKills.getKills();
                    case 9://corporeal beast
                        return player.corporealBeastKills.getKills();
                    case 10://crazy archaeologist
                        return player.crazyArchaeologistKills.getKills();
                    case 11://dagannoth kings
                        return CollectionLog.addSumMultipleDagKC(player);
                    case 12://the fight caves
                        return 0;
                    //return player.theFightCaveKills.getKills();
                    case 13://the gauntlet
                        return 0;
                    //return player.theGauntletKills.getKills();
                    case 14://general graardor
                        return player.generalGraardorKills.getKills();
                    case 15://giant mole
                        return player.giantMoleKills.getKills();
                    case 16://grotesque guardians
                        // return 0;
                        return player.grotesqueGuardianKills.getKills();
                    case 17://hespori
                        //  return 0;
                        return player.hesporiKills.getKills();
                    case 18://inferno
                        return player.zukKills.getKills();
                    case 19://kalphite queen
                        return player.kalphiteQueenKills.getKills();
                    case 20://king black dragon
                        return player.kingBlackDragonKills.getKills();
                    case 21://kraken
                        return player.krakenKills.getKills();
                    case 22://kree'ara
                        return player.kreeArraKills.getKills();
                    case 23://k'ril
                        return player.krilTsutsarothKills.getKills();
                    case 24://obor
                        return player.oborKills.getKills();
                    case 25://sarachnis
                        return player.sarachnisKills.getKills();
                    case 26://scorpia
                        return player.scorpiaKills.getKills();
                    case 27://skotizo
                        return player.skotizoKills.getKills();
                    case 28://thermo devil
                        return player.thermonuclearSmokeDevilKills.getKills();
                    case 29://venenatis
                        return player.venenatisKills.getKills();
                    case 30://vet'ion
                        return player.vetionKills.getKills();
                    case 31://vorkath
                        return player.vorkathKills.getKills();
                    case 32://wintertodt
                        return player.wintertodtKills.getKills();
                    case 33://zalcano
                        return 0;
                    // return player.zalcanoKills.getKills();
                    case 34://zulrah
                        return player.zulrahKills.getKills();
                }
            case RAIDS:
                switch (entry) {
                    case 0://cox
                        return player.chambersofXericKills.getKills();
                    case 1://tob
                        return player.theatreOfBloodKills.getKills();
                }
            case CLUES:
                switch (entry) {
                    case 0://beginner treasure trails
                        return player.beginnerClueCount;
                    case 1://easy treasure trails
                        return player.easyClueCount;
                    case 2://medium treasure trails
                        return player.medClueCount;
                    case 3://hard treasure trails
                        return player.hardClueCount;
                    case 4://elite treasure trails
                        return player.eliteClueCount;
                    case 5://master treasure trails
                        return player.masterClueCount;
                    case 6://shared treasure trail rewards
                        return addSumMultipleClues(player);
                }
            case MINIGAMES:
                switch (entry) {
                    case 0://barbarian assault high-level gambles
                        return 0;
                }
            case OTHER:
                switch (entry) {
                    case 0:
                        return 0;
                    case 8://glough's experiments kills
                        return 0;
                    case 12://revenant kills
                        return 0;
                }
        }
        return entry;
    }

    /* adds up the killcount for dagannoth rex, supreme and prime */
    public static int addSumMultipleDagKC(Player player) {
        int sum = 0;
        int sum1 = player.dagannothRexKills.getKills();
        int sum2 = player.dagannothSupremeKills.getKills();
        int sum3 = player.dagannothPrimeKills.getKills();
        //    System.out.println("Sum for dag kc = " + sum);
        return sum1+sum2+sum3;
    }

    /* adds up all the clue count from beginner -> master */
    public static int addSumMultipleClues(Player player) {
        int sum = 0;
        int sum1 = player.beginnerClueCount;
        int sum2 = player.easyClueCount;
        int sum3 = player.medClueCount;
        int sum4 = player.hardClueCount;
        int sum5 = player.eliteClueCount;
        int sum6 = player.masterClueCount;
        //    System.out.println("Sum for clue kc = " + sum);
        return sum1+sum2+sum3+sum4+sum5+sum6;
    }

    /* adds up all the raids kc */
    public static int addRaidsKC(Player player) {
        int sum = 0;
        int sumCox = player.chambersofXericKills.getKills();
        int sumToB = player.theatreOfBloodKills.getKills();
        return sumCox+sumToB;
    }

    /* adds farmers set log items to collection log */
    public static void addLogItemFarmersSet(Player player) {
        if (player.getAppearance().isMale()) {
            player.getCollectionLog().add(13646, 1);//farmers strawhat
            player.getCollectionLog().add(13642, 1);//farmers shirt
            player.getCollectionLog().add(13640, 1);//farmers boro trousers
            player.getCollectionLog().add(13644, 1);//farmers boots
        } else {
            player.getCollectionLog().add(13647, 1);//farmers strawhat
            player.getCollectionLog().add(13643, 1);//farmers shirt
            player.getCollectionLog().add(13641, 1);//farmers boro trousers
            player.getCollectionLog().add(13645, 1);//farmers boots
        }
    }

    /* adds abyssal sire log items to collection log */
    public static void addLogItemAbyssalSire(Player player) {
        player.getCollectionLog().add(13262, 1);//abysal orphan
        player.getCollectionLog().add(13273, 1);//unsired
        player.getCollectionLog().add(7979, 1);//abyssal head
        player.getCollectionLog().add(13274, 1);//bludgeon spine
        player.getCollectionLog().add(13275, 1);//bludgeon claw
        player.getCollectionLog().add(13276, 1);//bludgeon axon
        player.getCollectionLog().add(13277, 1);//jar of miasma
        player.getCollectionLog().add(13265, 1);//abyssal dagger
        player.getCollectionLog().add(4151, 1);//abyssal whip
    }

    public static void addLogItemAbyssalSireAndInventory(Player player) {
        player.getCollectionLog().add(13262, 1);//abysal orphan
        player.getCollectionLog().add(13273, 1);//unsired
        player.getCollectionLog().add(7979, 1);//abyssal head
        player.getCollectionLog().add(13274, 1);//bludgeon spine
        player.getCollectionLog().add(13275, 1);//bludgeon claw
        player.getCollectionLog().add(13276, 1);//bludgeon axon
        player.getCollectionLog().add(13277, 1);//jar of miasma
        player.getCollectionLog().add(13265, 1);//abyssal dagger
        player.getCollectionLog().add(4151, 1);//abyssal whip

        player.getInventory().add(13262, 1);
        player.getInventory().add(13273, 1);
        player.getInventory().add(7979, 1);
        player.getInventory().add(13274, 1);
        player.getInventory().add(13275, 1);
        player.getInventory().add(13276, 1);
        player.getInventory().add(13277, 1);
        player.getInventory().add(13265, 1);
        player.getInventory().add(4151, 1);
    }

    /* adds barrows log items to collection log */
    public static void addLogItemBarrows(Player player) {

    }

    /* adds bryophyta log items to collection log */
    public static void addLogItemBryophyta(Player player) {
        player.getCollectionLog().add(22372, 1);//Bryophyta's essence
    }

    /* adds callisto log items to collection log */
    public static void addLogItemCallisto(Player player) {
        player.getCollectionLog().add(13178,1 );//callisto cub
        player.getCollectionLog().add(12603, 1);//tyrannical ring
        player.getCollectionLog().add(11920, 1);//dragon pickaxe
        player.getCollectionLog().add(7158, 1);//dragon 2h sword
    }

    /* adds cerberus' log items to collection log */
    public static void addLogItemCerberus(Player player) {
        player.getCollectionLog().add(13247, 1);//hellpuppy
        player.getCollectionLog().add(13227, 1);//eternal crystal
        player.getCollectionLog().add(13229, 1);//pegasian crystal
        player.getCollectionLog().add(13231, 1);//primordial crystal
        player.getCollectionLog().add(13245, 1);//jar of souls
        player.getCollectionLog().add(13233, 1);//smouldering stone
        player.getCollectionLog().add(13249, 1);//key master teleport
    }

    /* adds chaos elemental log items to collection log */
    public static void addLogItemChaosElemental(Player player) {
        player.getCollectionLog().add(11995,1 );//chaos ele pet
        player.getCollectionLog().add(11920, 1);//dragon pickaxe
        player.getCollectionLog().add(7158, 1);//dragon 2h sword
    }

    public static void handleBossesButtonSlots(Player player, int slot) {
        player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.BOSS, slot);
        player.getPacketSender().sendClientScript(2389, player.collection_log_current_tab = 0);
        player.getPacketSender().sendClientScript(2730, 40697866, 40697867, 40697868, 40697869, player.collection_log_current_struct = 471, player.collection_log_current_entry = slot);
    }

    public static void handleRaidsButtonSlots(Player player, int slot) {
        player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.RAIDS, slot);
        player.getPacketSender().sendClientScript(2389, player.collection_log_current_tab = 1);
        player.getPacketSender().sendClientScript(2730, 40697870, 40697871, 40697872, 40697877, player.collection_log_current_struct = 472, player.collection_log_current_entry = slot);
    }

    public static void handleCluesButtonSlots(Player player, int slot) {
        player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.CLUES, slot);
        player.getPacketSender().sendClientScript(2389, player.collection_log_current_tab = 2);
        player.getPacketSender().sendClientScript(2730, 40697878, 40697886, 40697887, 40697879, player.collection_log_current_struct = 473, player.collection_log_current_entry = slot);
    }

    public static void handleMinigamesButtonSlots(Player player, int slot) {
        player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.MINIGAMES, slot);
        player.getPacketSender().sendClientScript(2389, player.collection_log_current_tab = 3);
        player.getPacketSender().sendClientScript(2730, 40697880, 40697881, 40697890, 40697882, player.collection_log_current_struct = 474, player.collection_log_current_entry = slot);
    }

    public static void handleOtherButtonSlots(Player player, int slot) {
        player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.OTHER, slot);
        player.getPacketSender().sendClientScript(2389, player.collection_log_current_tab = 4);
        player.getPacketSender().sendClientScript(2730, 40697883, 40697888, 40697889, 40697884, player.collection_log_current_struct = 475, player.collection_log_current_entry = slot);
    }

    static {
        try {
            ItemAction.registerInventory(COLLECTION_LOG_ITEM_ID, 1, (player, item) -> {
                player.getCollectionLog().openCollectionLog(player);
                return;
            });

            InterfaceHandler.register(COLLECTION_LOG_ID, h -> {

                /* handles the bosses button slots */
                h.actions[11] = (DefaultAction) (player, option, slot, itemId) -> handleBossesButtonSlots(player, slot);

                /* handles the raids button slots */
                h.actions[15] = (DefaultAction) (player, option, slot, itemId) -> handleRaidsButtonSlots(player, slot);

                /* handles the clues button slots */
                h.actions[30] = (DefaultAction) (player, option, slot, itemId) -> handleCluesButtonSlots(player, slot);

                /* handles the minigames button slots */
                h.actions[25] = (DefaultAction) (player, option, slot, itemId) -> handleMinigamesButtonSlots(player, slot);

                /* handles the other button slots */
                h.actions[32] = (DefaultAction) (player, option, slot, itemId) -> handleOtherButtonSlots(player, slot);

                /*            *//* handles the bosses button slots *//*
            h.actions[11] = (DefaultAction) (player, option, slot, itemId) -> {
                player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.BOSS, slot);
                player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 0);
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697866, 40697867, 40697868, 40697869, player.collection_log_current_struct = 471, player.collection_log_current_entry = slot);
            };

            *//* handles the raids button slots *//*
            h.actions[15] = (DefaultAction) (player, option, slot, itemId) -> {
                player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.RAIDS, slot);
                player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 1);
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697870, 40697871, 40697872, 40697877, player.collection_log_current_struct = 472, player.collection_log_current_entry = slot);
            };

            *//* handles the clues button slots *//*
            h.actions[30] = (DefaultAction) (player, option, slot, itemId) -> {
                player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 2);
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697878, 40697886, 40697887, 40697879, player.collection_log_current_struct = 473, player.collection_log_current_entry = slot);
                player.getCollectionLog().getCollectionLogKillCount(player, CollectionLogInfo.CLUES, player.collection_log_current_entry = slot);

            };

            *//* handles the minigames button slots *//*
            h.actions[25] = (DefaultAction) (player, option, slot, itemId) -> {
                player.getCollectionLog().handleKillCountSlot(player, CollectionLogInfo.MINIGAMES, slot);
                player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 3);
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697880, 40697881, 40697890, 40697882, player.collection_log_current_struct = 474, player.collection_log_current_entry = slot);
                player.getCollectionLog().getCollectionLogKillCount(player, CollectionLogInfo.MINIGAMES, player.collection_log_current_entry = slot);
            };

            *//* handles the other button slots *//*
            h.actions[32] = (DefaultAction) (player, option, slot, itemId) -> {
                player.getPacketSender().sendClientScript(2389, "i", player.collection_log_current_tab = 4);
                player.getPacketSender().sendClientScript(2730, "iiiiii", 40697883, 40697888, 40697889, 40697884, player.collection_log_current_struct = 475, player.collection_log_current_entry = slot);
                player.getCollectionLog().getCollectionLogKillCount(player, CollectionLogInfo.OTHER, player.collection_log_current_entry = slot);
            };*/

                /* handles the switching of tabs */
                h.actions[4] = (SimpleAction) p -> sendBossesTab(p);//p.getCollectionLog().handleLogTab(p, 4, 0));
                h.actions[5] = (SimpleAction) p -> sendRaidsTab(p);//p.getCollectionLog().handleLogTab(p, 5, 1));
                h.actions[6] = (SimpleAction) p -> sendCluesTab(p);//p.getCollectionLog().handleLogTab(p, 6, 2));
                h.actions[7] = (SimpleAction) p -> sendMinigamesTab(p);//p.getCollectionLog().handleLogTab(p, 7, 3));
                h.actions[8] = (SimpleAction) p -> sendOtherTab(p);//p.getCollectionLog().handleLogTab(p, 8, 4));

                h.actions[27] = (SimpleAction) p -> sendOtherTab(p);//p.getCollectionLog().handleLogTab(p, 8, 4));

                /* on interface close */
                h.closedAction = (p, i) -> {
                    p.getPacketSender().sendClientScript(101, "i", 11);
                };
            });

        }catch (Throwable e){
            System.out.println(e);
        }
    }

    @Override
    protected CollectionLogItem newItem(int id, int amount, Map<String, String> attributes) {
        return new CollectionLogItem(id, amount, attributes);
    }

    @Override
    protected CollectionLogItem[] newArray(int size) {
        return new CollectionLogItem[size];
    }

    public boolean sendUpdates() {
        return sendUpdates(null);
    }

}