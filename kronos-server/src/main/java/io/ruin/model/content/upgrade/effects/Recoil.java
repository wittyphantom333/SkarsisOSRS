package io.ruin.model.content.upgrade.effects;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.Hit;
import io.ruin.model.content.upgrade.ItemUpgrade;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.player.Player;
import io.ruin.model.item.Item;

/**
 * @author ReverendDread on 6/18/2020
 * https://www.rune-server.ee/members/reverenddread/
 * @project Kronos
 */
public class Recoil extends ItemUpgrade {

    @Override
    public void postPlayerDamage(Player player, Entity target, Item item, Hit hit) {
        if (hit.attacker != null && hit.attacker.npc != null && hit.damage > 0 && Random.rollDie(10)) {
            hit.attacker.npc.hit(new Hit().fixedDamage(hit.damage / 2));
        }
    }

}
