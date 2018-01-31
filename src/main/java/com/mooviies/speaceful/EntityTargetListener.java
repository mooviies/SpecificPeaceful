package com.mooviies.speaceful;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetListener implements Listener {
    private SelectivePeaceful plugin;

    public EntityTargetListener(SelectivePeaceful plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(EntityTargetEvent event)
    {
        if(event.getTarget() == null)
            return;

        if(plugin.playerIsOnPeaceful(event.getTarget().getUniqueId()))
        {
            if(event.getEntityType() != EntityType.WOLF && event.getEntityType() != EntityType.POLAR_BEAR && event.getEntityType() != EntityType.LLAMA)
                event.setTarget(null);
        }
    }
}
