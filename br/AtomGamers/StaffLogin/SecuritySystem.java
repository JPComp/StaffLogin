package br.AtomGamers.StaffLogin;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class SecuritySystem extends SLogin {

    protected List<Player> logged = new ArrayList<Player>();
    
    protected boolean isLogged(Player sender) {
        return logged.contains(sender);
    }
    
    protected void setLogged(Player sender) {
        logged.add(sender);
    }
    
    protected void setUnlogged(Player sender) {
        logged.remove(sender);
    }
    
    protected void setPlayerPassword(String sender, Object password) {
        getConfig().set("Staffers." + sender, password);
        saveConfig();
    }

    protected Object getPlayerPassword(Player sender) {
        reloadConfig();
        return getConfig().get("Staffers." + sender.getName());
    }
    
    protected boolean isCorrectPassword(Player sender, Object password) {
        reloadConfig();
        return getConfig().get("Staffers." + sender.getName()).equals(password);
    }
    
}
