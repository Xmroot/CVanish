package legacystore.store.cvanish;

import com.github.justadeni.HexColorLib;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.regex.Pattern;

public final class CVanish extends JavaPlugin {

    private static HexColorLib hex = HexColorLib.INSTANCE;

    private final Pattern pattern = Pattern.compile("[a-fA-F0-9]{6}");

    public static String sem_perm = "#9C1111Você não tem permisspara usar esse comando.";

    public static CVanish instance;
    PluginManager vanish = getServer().getPluginManager();
    public static Plugin getPlugin(){
        return (Plugin)instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        (new BukkitRunnable() {
            public void run() {
                for(Player jogador : Bukkit.getOnlinePlayers()){
                    if(ComandoVanish.admin.contains(jogador.getName())) {
                        if(ComandoVanish.admin.size() < 2) {
                            ActionBar.enviar(jogador, "#DCDC17Somente você está no modo Vanish.");
                            continue;
                        }
                        ActionBar.enviar(jogador, "#DCDC17Você e mais #F5F5D4" + (ComandoVanish.admin.size() - 1) + "#DCDC17staff(s) estão no Vanish.");
                    }
                }
            }
        }).runTaskTimer((Plugin)instance, 1L, 20L);
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" §bDesenvolvido por Legacy Store");
        Bukkit.getConsoleSender().sendMessage(" §aPlugin Inicializado.");
        Bukkit.getConsoleSender().sendMessage(" §fAdquira nossos plugins em: §bdiscord.gg/D6mCqh6bu5");
        Bukkit.getConsoleSender().sendMessage(" ");
        getCommand("v").setExecutor((CommandExecutor)new ComandoVanish());
        this.vanish.registerEvents((Listener)new Eventos(), (Plugin)this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(" §bDesenvolvido por Legacy Store");
        Bukkit.getConsoleSender().sendMessage(" §bObrigado pela preferencia!");
        Bukkit.getConsoleSender().sendMessage(" §fAdquira nossos plugins em: §bdiscord.gg/D6mCqh6bu5");
        Bukkit.getConsoleSender().sendMessage(" ");
    }
}
