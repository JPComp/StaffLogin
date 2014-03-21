package br.AtomGamers.StaffLogin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class StaffLoginEvent extends SecuritySystem implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onLogin(PlayerLoginEvent e) {
        Player sender = e.getPlayer();
        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                sender.sendMessage("§b[Staff] §fVocê logou no Servidor, use /login <sua senha> e depois:");
                sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onMoveDenied(PlayerMoveEvent e) {
        Player sender = e.getPlayer();
        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                e.setCancelled(true);
                sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onQuitStaff(PlayerQuitEvent e) {
        Player sender = e.getPlayer();
        if (isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                setUnlogged(sender);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onCommandDenied(PlayerCommandPreprocessEvent e) {
        Player sender = e.getPlayer();
        String cmd = e.getMessage().toLowerCase();

        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                if (!cmd.startsWith("/login") && !cmd.startsWith("/register") && !cmd.startsWith("/stafflogin")) {
                    e.setCancelled(true);
                    sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onInventoryOpenDenied(InventoryOpenEvent e) {
        Player sender = (Player) e.getPlayer();
        if (sender instanceof Player) {
            if (!isLogged(sender)) {
                if (sender.hasPermission("stafflogin.login")) {
                    e.setCancelled(true);
                    sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onItemDropDenied(PlayerDropItemEvent e) {
        Player sender = e.getPlayer();

        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                e.setCancelled(true);
                sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBlockBreakDenied(BlockBreakEvent e) {
        Player sender = e.getPlayer();

        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                e.setCancelled(true);
                sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onBlockPlaceDenied(BlockPlaceEvent e) {
        Player sender = e.getPlayer();

        if (!isLogged(sender)) {
            if (sender.hasPermission("stafflogin.login")) {
                e.setCancelled(true);
                sender.sendMessage("§b[Staff] §fUtilize o comando de Login da STAFF para entrar!");
            }
        }
    }
}
