package com.mooviies.speaceful;

import com.mooviies.speaceful.entities.*;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashSet;
import java.util.UUID;

public class SelectivePeaceful extends JavaPlugin {
    public String pluginFolder = getDataFolder().getAbsolutePath();
    public String saveFileName = "playersOnPeaceful.dat";
    private HashSet<UUID> playersUUIDOnPeaceful = new HashSet<>();
    private static SelectivePeaceful instance;

    public static SelectivePeaceful getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Load();

        NMSUtils.registerEntity(NMSUtils.Type.BLAZE, CustomEntityBlaze.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.CAVE_SPIDER, CustomEntityCaveSpider.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.CREEPER, CustomEntityCreeper.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.ENDERMITE, CustomEntityEndermite.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.GHAST, CustomEntityGhast.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.HUSK, CustomEntityHusk.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.SKELETON, CustomEntitySkeleton.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.SPIDER, CustomEntitySpider.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.STRAY, CustomEntityStray.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.VINDICATOR, CustomEntityVindicator.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.WITCH, CustomEntityWitch.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.ZOMBIE, CustomEntityZombie.class, true);
        NMSUtils.registerEntity(NMSUtils.Type.ZOMBIE_VILLAGER, CustomEntityZombieVillager.class, true);
    }

    @Override
    public void onDisable() {
        Save();
    }

    public boolean playerIsOnPeaceful(Player player)
    {
        if(player == null)
            return false;

        return playersUUIDOnPeaceful.contains(player.getUniqueId());
    }

    public boolean playerIsOnPeaceful(OfflinePlayer player)
    {
        if(player == null)
            return false;

        return playersUUIDOnPeaceful.contains(player.getUniqueId());
    }

    public boolean playerIsOnPeaceful(UUID uuid)
    {
        if(uuid == null)
            return false;

        return playersUUIDOnPeaceful.contains(uuid);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("selp") || label.equalsIgnoreCase("selectivepeaceful"))
        {
            UUID uuid = null;
            UUID senderUuid = null;
            Boolean mode = null;
            String playerName = null;

            if(args.length > 2)
                return false;

            if(args.length == 2)
            {
                String modeValue = args[1];
                if(modeValue.equalsIgnoreCase("true") || modeValue.equals("1"))
                    mode = true;
                else if(modeValue.equalsIgnoreCase("false") || modeValue.equals("0"))
                    mode = false;
                else
                    return false;

                playerName = args[0];
            }

            if(playerName == null && args.length == 1)
            {
                if(mode != null)
                    return false;

                String modeValue = args[0];
                if(modeValue.equalsIgnoreCase("true") || modeValue.equals("1"))
                    mode = true;
                else if(modeValue.equalsIgnoreCase("false") || modeValue.equals("0"))
                    mode = false;
                else
                    playerName = args[0];
            }

            if(playerName == null)
            {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    uuid = player.getUniqueId();
                    senderUuid = uuid;
                    playerName = player.getName();
                }
                else
                    return false;
            }
            else
            {
                Player player = getServer().getPlayer(playerName);
                if(player != null)
                    uuid = player.getUniqueId();
            }

            if(uuid == null)
            {
                sender.sendMessage(ChatColor.RED + "Couldn't find player : " + playerName);
                return true;
            }

            if(mode == null)
            {
                String prefix;

                if(uuid == senderUuid)
                    prefix = "You are ";
                else
                    prefix = playerName + " is ";

                if(playersUUIDOnPeaceful.contains(uuid))
                {
                    sender.sendMessage(ChatColor.GREEN + prefix + "on the peaceful list");
                }
                else
                {
                    sender.sendMessage(prefix + "not on the peaceful list");
                }
                return true;
            }

            String isPrefix, wasPrefix;

            if(uuid == senderUuid)
            {
                isPrefix = "You are ";
                wasPrefix = "You were ";
            }
            else
            {
                isPrefix = playerName + " is ";
                wasPrefix = playerName + " was ";
            }

            if(mode)
            {
                if(playersUUIDOnPeaceful.contains(uuid))
                {
                    sender.sendMessage(isPrefix + "already on the peaceful list");
                    return true;
                }

                playersUUIDOnPeaceful.add(uuid);
                sender.sendMessage(ChatColor.GREEN + wasPrefix + "added on the peaceful list");
            }
            else
            {
                if(!playersUUIDOnPeaceful.contains(uuid))
                {
                    sender.sendMessage(isPrefix + "already off the peaceful list");
                    return true;
                }

                playersUUIDOnPeaceful.remove(uuid);
                sender.sendMessage(wasPrefix + "removed from the peaceful list");
            }

            Save();
            return true;
        }
        return false;
    }

    private void Save()
    {
        File dir = new File(pluginFolder);
        if(!dir.exists())
            dir.mkdir();

        try {
            FileOutputStream file = new FileOutputStream(pluginFolder + File.separator + saveFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(playersUUIDOnPeaceful);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getLogger().info("SelectivePeaceful : PlayersOnPeaceful data saved");
    }

    private void Load()
    {
        File tmpFile = new File(pluginFolder + File.separator + saveFileName);
        if(!tmpFile.exists())
            return;

        try {
            FileInputStream file = new FileInputStream(pluginFolder + File.separator + saveFileName);
            ObjectInputStream in = new ObjectInputStream(file);
            playersUUIDOnPeaceful = (HashSet<UUID>) in.readObject();
            in.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getLogger().info("SelectivePeaceful : PlayersOnPeaceful data loaded");
    }
}
