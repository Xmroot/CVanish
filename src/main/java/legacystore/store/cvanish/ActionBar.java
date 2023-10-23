package legacystore.store.cvanish;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    private static PacketPlayOutChat packet;

    public static void enviar(Player p, String texto) {
        PacketPlayOutChat packets = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + texto + "\"}"), (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet) packets);
    }

    public static void enviarTodos(String texto) {
        PacketPlayOutChat packets = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + texto + "\"}"), (byte)2);
        for (Player p : Bukkit.getServer().getOnlinePlayers())
            (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet) packets);
    }
}
