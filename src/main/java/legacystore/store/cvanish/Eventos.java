package legacystore.store.cvanish;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Eventos implements Listener {

    @EventHandler
    public void entrar(PlayerJoinEvent e){
        for(Player ps : Bukkit.getOnlinePlayers()) {
            if (ComandoVanish.admin.contains(ps.getName()) &&
            !e.getPlayer().hasPermission("blockcraft.vanish"))
                e.getPlayer().hidePlayer(ps);
        }
    }

    public void entityDamage(EntityDamageEvent e){
        if(e.getEntityType() == EntityType.PLAYER) {
            Player p = (Player)e.getEntity();
            if(ComandoVanish.admin.contains(p.getName()))
                e.setCancelled(true);
        }
    }

    @EventHandler
    private void OnPlayerDropItemAdmin(PlayerDropItemEvent e){
        Player jogador = e.getPlayer();
        if(ComandoVanish.admin.contains(jogador.getName())); {
            jogador.sendMessage(" ");
            jogador.sendMessage(" #9C1111Você não pode dropar itens no modo vanish.");
            jogador.sendMessage(" ");
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void onPlayerPickUpAdmin(PlayerPickupItemEvent e) {
        Player jogador = e.getPlayer();
        if(ComandoVanish.admin.contains(e.getPlayer().getName())) {
            ComandoVanish.admin.remove(e.getPlayer().getName());
            e.getPlayer().getInventory().clear();
        }
    }

    @EventHandler
    public static void adminSair(PlayerQuitEvent e){
        if(ComandoVanish.admin.contains(e.getPlayer().getName())) {
            ComandoVanish.admin.remove(e.getPlayer().getName());
            e.getPlayer().getInventory().clear();
        }
    }

    @EventHandler
    public static void adminDeath(PlayerDeathEvent e) {
        if(ComandoVanish.admin.contains(e.getEntity().getName())) {
            ComandoVanish.admin.remove(e.getEntity().getName());
            e.getDrops().clear();
        }
    }

    @EventHandler
    public static void adminGamemode(PlayerGameModeChangeEvent e) {
        if(ComandoVanish.admin.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    public static ItemStack buildItem(Material mat, String display) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display.replace("&", "§"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack buildItem(Material mat, String display, int ItemID) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display.replace("&", "§"));
        item.setItemMeta(meta);
        item.setDurability((short) ItemID);
        return item;
    }

    @EventHandler
    public void onPlayerIntereract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material m = p.getItemInHand().getType();
        if(ComandoVanish.admin.contains(e.getPlayer().getName()) && (
                e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
        if(m == Material.SLIME_BALL) {
            e.setCancelled(true);
            mensagemadmin(p);
        } else if (m == Material.LEGACY_FIREBALL) {
            e.setCancelled(true);
            mensagemadmin2(p);
        }
    }

    public void mensagemadmin(Player p) {
        p.getInventory().clear();
        p.sendMessage("");
        p.sendMessage(" §cCuidado! Você está visivel agora.");
        p.sendMessage("");
    }

    public void mensagemadmin2(Player p) {
        p.getInventory().clear();
        p.sendMessage("");
        p.sendMessage(" §aVocê invisivel agora.");
        p.sendMessage("");
    }


}
