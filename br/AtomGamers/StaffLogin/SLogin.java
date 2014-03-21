package br.AtomGamers.StaffLogin;

import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SLogin extends JavaPlugin {

    @Override
    public void onEnable() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                saveResource("config_template.yml", false);
                File file2 = new File(getDataFolder(), "config_template.yml");
                file2.renameTo(new File(getDataFolder(), "config.yml"));
            } catch (Exception e) {
            }
        }
        reloadConfig();
        getServer().getPluginManager().registerEvents(new StaffLoginEvent(), this);
        getServer().getConsoleSender().sendMessage("§b[StaffLogin] §fPlugin inicializado. (Autor=AtomGamers)");
    }

    @Override
    public void onDisable() {
        for (Player p : getServer().getOnlinePlayers()) {
            p.kickPlayer("Server closed.");
        }
        saveConfig();
    }
}
