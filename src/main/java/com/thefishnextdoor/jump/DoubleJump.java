package com.thefishnextdoor.jump;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.thefishnextdoor.jump.command.DoubleJumpCommand;
import com.thefishnextdoor.jump.event.EntityDamage;
import com.thefishnextdoor.jump.event.PlayerChangedWorld;
import com.thefishnextdoor.jump.event.PlayerCommandPreprocess;
import com.thefishnextdoor.jump.event.PlayerMove;
import com.thefishnextdoor.jump.event.PlayerQuit;
import com.thefishnextdoor.jump.event.PlayerToggleFlight;

public class DoubleJump extends JavaPlugin {

    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new PlayerToggleFlight(), this);
        pluginManager.registerEvents(new EntityDamage(), this);
        pluginManager.registerEvents(new PlayerQuit(), this);
        pluginManager.registerEvents(new PlayerChangedWorld(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocess(), this);
        getCommand("doublejump").setExecutor(new DoubleJumpCommand());
        getLogger().info("Plugin enabled");
    }

    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}