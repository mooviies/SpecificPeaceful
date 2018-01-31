package com.mooviies.speaceful;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    private SelectivePeaceful plugin;

    public EntityDamageByEntityListener(SelectivePeaceful plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() == null)
            return;

        if(plugin.playerIsOnPeaceful(event.getEntity().getUniqueId()))
        {
            if(event.getDamager() == null)
                return;

            EntityType type = event.getDamager().getType();

            if(type != EntityType.LLAMA &&
                    type != EntityType.LLAMA_SPIT &&
                    type != EntityType.ARROW &&
                    type != EntityType.SPECTRAL_ARROW &&
                    type != EntityType.TIPPED_ARROW &&
                    type != EntityType.LIGHTNING &&
                    type != EntityType.AREA_EFFECT_CLOUD &&
                    type != EntityType.SPLASH_POTION &&
                    type != EntityType.LINGERING_POTION)
                event.setCancelled(true);
        }
    }
}
