package io.ruin.model.entity.player;

import io.ruin.api.utils.NumberUtils;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.actions.SlotAction;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.Item;
import io.ruin.model.item.ItemContainer;
import io.ruin.model.map.object.actions.ObjectAction;

public class DeadmanRaidManager {

    public static final int CHEST = 27269;

    static {
        ObjectAction.register(CHEST, "open", (player, obj) -> {
            boolean hasKey = false;
            for (int i = 0; i < 5; i++) {
                if (player.getInventory().contains(13302 + i)) {
                    hasKey = true;
                }
            }
            if (!hasKey) {
                player.sendMessage("You do not have any bank keys.");
            } else {
                player.getDeadmanRaidManager().openChest();
            }
        });
        InterfaceHandler.register(Interface.DEADMAN_CHEST, h -> {
            h.actions[8] = (SlotAction) (p, s) -> {
                p.getDeadmanRaidManager().remove(p.getDeadmanRaidManager().firstSlot);
                p.closeInterface(InterfaceType.MAIN);
            };
            h.actions[14] = (SlotAction) (p, s) -> p.getDeadmanRaidManager().firstSlot = s;
            h.actions[26] = (SimpleAction) p -> p.getDeadmanRaidManager().withdrawAsNote = false;
            h.actions[31] = (SimpleAction) p -> p.getDeadmanRaidManager().withdrawAsNote = true;
            h.actions[37] = (SlotAction) (p, s) -> {
                if (p.getInventory().contains(13302 + p.getDeadmanRaidManager().firstSlot)) {
                    p.getInventory().remove(new Item(13302 + p.getDeadmanRaidManager().firstSlot));
                    for (Item item : p.getDeadmanRaidManager().get(p.getDeadmanRaidManager().firstSlot)) {
                        p.getInventory().addOrDrop(item);
                    }
                    p.getDeadmanRaidManager().remove(p.getDeadmanRaidManager().firstSlot);
                }
                p.closeInterface(InterfaceType.MAIN);
                p.getPacketSender().sendItems(0, 0, 558 + p.getDeadmanRaidManager().currentTab, new Item[] { });
                p.getPacketSender().sendClientScript(29);
            };
            h.actions[44] = (SlotAction) (p, s) -> {
                if (p.getInventory().contains(13302 + p.getDeadmanRaidManager().firstSlot)) {
                    p.getInventory().remove(new Item(13302 + p.getDeadmanRaidManager().firstSlot));
                    for (Item item : p.getDeadmanRaidManager().get(p.getDeadmanRaidManager().firstSlot)) {
                        p.getInventory().addOrDrop(item);
                    }
                    p.getDeadmanRaidManager().remove(p.getDeadmanRaidManager().firstSlot);
                }
                p.closeInterface(InterfaceType.MAIN);
                p.getPacketSender().sendItems(0, 0, 558 + p.getDeadmanRaidManager().currentTab, new Item[] { });
                p.getPacketSender().sendClientScript(29);
            };
        });

    }

    private Player player;

    private int currentTab = 0, firstSlot = -1;
    private boolean withdrawAsNote;

    private ItemContainer[] raids = new ItemContainer[10];

    public DeadmanRaidManager(Player player) {
        this.player = player;
    }

    public void openChest() {
        int counts[] = new int[5];
        for (int i = 0; i < 5; i++) {
            counts[i] = player.getInventory().getAmount(13302 + i);
        }
        if (firstSlot == -1) {
            for (int i = 0; i < 5; i++) {
                if (counts[i] > 0) {
                    firstSlot = i;
                    break;
                }
            }
        }
        if (firstSlot == -1) {
            firstSlot = 0;
        }
        player.openInterface(InterfaceType.MAIN, Interface.DEADMAN_CHEST);
        player.getPacketSender().sendClientScript(1166);
        player.getPacketSender().sendClientScript(1171);
        player.getPacketSender().sendClientScript(1170);
        Config.DEADMAN_CHEST_CURRENT_TAB.set(player, currentTab);
        player.getPacketSender().sendClientScript(1178, "ii", Interface.DEADMAN_CHEST << 16 | 46, Interface.DEADMAN_CHEST << 16 | 47);
        player.getPacketSender().sendClientScript(1163);
        if (player.getDeadmanRaidManager().size() == 0) {
            return;
        }
        player.getPacketSender().sendItems(0, 0, 558 + currentTab, player.getDeadmanRaidManager().get(firstSlot));
        int amt = 0;
        String value = "";
        for (Item item : player.getDeadmanRaidManager().get(firstSlot)) {
            if (item == null) continue;
            amt += item.getDef() == null ? 0 : item.getDef().getPrice() * item.getAmount();
        }
        float total = amt;
        if (amt > 1000000) {
            value = (total / 1000000) + "";
            if (value.contains(".")) {
                value = value.substring(0, value.indexOf('.') + 2);
            }
            value += "M";
        } else {
            if (amt > 100000) {
                value = (total / 100000) + "";
                if (value.contains(".")) {
                    value = value.substring(0, value.indexOf('.') + 2);
                }
                value += "K";
            } else {
                value = NumberUtils.formatNumber(amt);
            }
        }
        player.getPacketSender().sendClientScript(1169, "si", currentTab, value);
    }

    public boolean add(ItemContainer raid) {
//        if (player.getGameMode() != GameMode.NORMAL) {
//            player.sendMessage("<col=ff0000>As an Ironman you're not able to receive any Bank Keys.");
//            return true;
//        }
        for (int i = 0; i < raids.length; i++) {
            if (raids[i] == null || !player.hasItem(new Item(13302 + i))) {
                raids[i] = raid;
                player.getInventory().addOrDrop(new Item(13302 + i));
                return true;
            }
        }
        return false;
    }

    public Item[] get(int currentTab) {
        return raids[currentTab] == null ? new Item[0] : raids[currentTab].getItems();
    }

    public void remove(int tab) {
        raids[tab] = null;
        player.getInventory().remove(new Item(13302 + tab, 500));
        player.getBank().remove(new Item(13302 + tab, 500));
    }

    public int size() {
        int count = 0;
        for (int i = 0 ; i < raids.length; i++) {
            if (raids[i] != null) count++;
        }
        return count;
    }

}
