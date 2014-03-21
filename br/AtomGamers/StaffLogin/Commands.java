package br.AtomGamers.StaffLogin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands extends SecuritySystem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String str, String[] args) {

        if (!(cs instanceof Player)) {
            getServer().getConsoleSender().sendMessage("§b[StaffLogin] §fComando desabilitado no Console.");
            return true;
        }

        Player sender = (Player) cs;

        if (cmd.getName().equalsIgnoreCase("stafflogin")) {
            if (sender.hasPermission("stafflogin.login")) {
                if (args.length == 0) {
                    sender.sendMessage("§b[StaffLogin] §f/stafflogin <sua-senha>");
                } else if (args.length > 0) {
                    if (isLogged(sender)) {
                        sender.sendMessage("§b[StaffLogin] §fVocê já está logado(a)");
                    } else {
                        Object password = args[1];
                        if (isCorrectPassword(sender, password)) {
                            setLogged(sender);
                        } else {
                            sender.kickPlayer("§b[StaffLogin] §cSENHA INCORRETA!");
                        }
                    }
                }
            } else {
                sender.sendMessage("§b[StaffLogin] §cVocê não é um Staffer.");
            }
        }
        
        if (cmd.getName().equalsIgnoreCase("staffregister")) {
            if (sender.hasPermission("stafflogin.register")) {
                if (args.length < 2) {
                    sender.sendMessage("§b[StaffLogin] §f/staffregister <player> <password>");
                } else if (args.length >= 2) {
                    Object password = args[1];
                    setPlayerPassword(args[0], password);
                    sender.sendMessage("§b[StaffLogin] §fStaff Password '"+args[1]+"' setado para o Staffer '"+args[0]+"'!");
                }
            } else {
                sender.sendMessage("§b[StaffLogin] §cSem permissão.");
            }
        }

        return false;
    }
}
