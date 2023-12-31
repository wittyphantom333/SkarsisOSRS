package io.ruin.model.item.actions.impl;

import io.ruin.api.utils.NumberUtils;
import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.cache.NPCDef;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.impl.jewellery.RingOfWealth;
import io.ruin.model.map.Position;
import io.ruin.model.map.ground.GroundItem;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import static io.ruin.cache.ItemID.COINS_995;
import static io.ruin.model.item.actions.impl.boxes.mystery.PVPArmourMysteryBox.PVP_ARMOUR_BOX_TABLE;
import static java.lang.Math.sqrt;

public enum PvpWeapon {
    BOX(22330, 1, 1,
            npc -> (sqrt(npc.getDef().combatLevel)) / 1000,
            npc -> npc.getDef().combatLevel >= 1);

    PvpWeapon(int itemId, int minAmount, int maxAmount, Function<NPC, Double> dropChance, Predicate<NPC> dropTest) {
        this.itemId = itemId;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.dropChance = dropChance;
        this.dropTest = dropTest;
    }

    private final int itemId;
    private final int minAmount, maxAmount; // range of coins this casket will give
    private final Function<NPC, Double> dropChance; // probability [0, 1] of the given NPC dropping this casket
    private final Predicate<NPC> dropTest; // should the given npc have a chance at dropping this casket?

    public int getItemId() {
        return itemId;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public Function<NPC, Double> getDropChance() {
        return dropChance;
    }

    public Predicate<NPC> getDropTest() {
        return dropTest;
    }

    public static void drop(Player player, NPC npc, Position dropPosition) {
        PvpWeapon casket = roll(player, npc);
        if (casket == null) {
            return;
        }
        new GroundItem(22330, 1).owner(player).position(dropPosition).spawn();
    }

    public static PvpWeapon roll(Player player, NPC npc) {
        PvpWeapon casket = getCasket(npc);
        if (casket == null) {
            return null;
        }
        double additiveBoost = 0;
        double multiplier = 1;
        if(RingOfWealth.wearingRingOfWealth(player)) {
            multiplier *= 1.0;
        }
        if (npc.wildernessSpawnLevel > 0) {
            multiplier *= 1.0;
        }
        if (!casket.roll(npc, additiveBoost, multiplier)) {
            return null;
        }
        return casket;
    }

    private boolean roll(NPC npc, double additiveBoost, double multiplier) {
        return Random.get() <= ((dropChance.apply(npc) + additiveBoost) * (multiplier));
    }

    public static PvpWeapon getCasket(NPC npc) {
        for (PvpWeapon goldCasket : PvpWeapon.values()) {
            if (goldCasket.dropTest.test(npc))
                return goldCasket;
        }
        return null;
    }

    private static void open(Player player, Item item, int minAmount, int maxAmount) {
        Item reward = PVP_ARMOUR_BOX_TABLE.rollItem();
        item.remove();
        player.getInventory().add(reward);
        player.sendFilteredMessage(Color.DARK_GREEN.wrap("You open the mystery box to find a " + reward.getDef().name + "."));
    }

    private static void set(double chance, int...ids) {
        for (int id : ids)
            NPCDef.get(id).giantCasketChance = chance;
    }

    private static void set(double chance, String... names) {
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].toLowerCase();
        }
        NPCDef.cached.values().stream()
                .filter(def -> def.name != null && Arrays.asList(names).contains(def.name.toLowerCase()))
                .forEach(def -> def.giantCasketChance = chance);
    }

    private static final int ELVARG = 6118;
    private static final int KRAKEN_BOSS = 494;

    /*
    static {
        for (PvpWeapon goldCasket : PvpWeapon.values())
            ItemAction.registerInventory(goldCasket.itemId, 1, (player, item) -> open(player, item, goldCasket.minAmount, goldCasket.maxAmount));
        set(0.6, "general graardor", "commander zilyana", "kree'arra", "k'ril tsutsaroth");
        set(0.5, "callisto", "venenatis");
        set(0.7 / 1.2, "chaos elemental", "vet'tion");
        set(0.5, "crazy archeologist", "chaos fanatic", "scorpia");
        set(0.45, "kalphite queen");
        set(1/3.0, "king black dragon");
        set(1/3.0, "thermonuclear smoke devil");
        set(0.5, "cerberus");
        set(1, "corporeal beast");
        set(1/3.0, "dagannoth rex", "dagannoth prime", "dagannoth supreme");
        set(2.0/5, ELVARG);
        set(1.0/3.0, KRAKEN_BOSS);
        set(0.15, "zulrah");
        set(1/2.0, "vorkath");
        set(0.4, "giant mole");
        set(1/2.0, "alchemical hydra");
    }
    */
}
