package legacystore.store.cvanish;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class ComandoVanish implements CommandExecutor {

    public static ArrayList<String> admin = new ArrayList<>();
    public static ArrayList<Player> adminMode = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 0) {
                if(p.hasPermission("blockcraft.vanish")) {
                    if(admin.contains(p.getName())) {
                        p.getInventory().clear();
                        p.getInventory().setArmorContents(null);
                        admin.remove(p.getName());
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.sendTitle("§cModo Vanish", "§fDesativado com sucesso!");
                        for (Player ps : Bukkit.getOnlinePlayers())
                            ps.showPlayer(p);
                    } else {
                        p.getInventory().clear();
                        p.getInventory().setArmorContents(null);
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        admin.add(p.getName());
                        p.sendTitle("§aModo Vanish", "§fAtivado com sucesso!");
                        p.getInventory().setItem(2, Eventos.buildItem(Material.SLIME_BALL, "§fVANISH §8- §aON"));
                        int EmAdmin = admin.size() - 1;
                        for (Player NoAdmin : Bukkit.getOnlinePlayers()) {
                            if(admin.contains(NoAdmin.getName()))
                                admin.contains(NoAdmin.getName());
                        }
                        for (Player ps2 : Bukkit.getOnlinePlayers()) {
                            if(!ps2.hasPermission("blockcraft.vanish"))
                                ps2.hidePlayer(p);
                        }
                    }
                } else {
                    p.sendMessage(CVanish.sem_perm);
                }
            } else {
                Bukkit.getConsoleSender().sendMessage("§cSomente jogadores podem executar esse comando.");
            }
        }
        return  false;
    }
}
