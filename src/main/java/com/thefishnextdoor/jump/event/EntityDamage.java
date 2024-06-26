package com.thefishnextdoor.jump.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.thefishnextdoor.jump.DoubleJump;
import com.thefishnextdoor.jump.PlayerProfile;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() != DamageCause.FALL) {
            return;
        }
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;
        PlayerProfile playerProfile = PlayerProfile.get(player);
        if (!playerProfile.doubleJumpEnabled()) {
            return;
        }
        double damage = event.getDamage();
        damage -= DoubleJump.getSettings().FALL_DAMAGE_REDUCTION;
        if (damage <= 0.0) {
            event.setCancelled(true);
        }
        else {
            event.setDamage(damage);
        }
    }
}