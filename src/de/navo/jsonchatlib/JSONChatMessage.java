package de.navo.jsonchatlib;

import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;

public class JSONChatMessage {
    private final JSONObject chatObject;

    @SuppressWarnings("unchecked")
	public JSONChatMessage(String text, JSONChatColor color, List<JSONChatFormat> formats) {
        chatObject = new JSONObject();
        chatObject.put("text", text);
        if (color != null) {
            chatObject.put("color", color.getColorString());
        }
        if (formats != null) {
            for (JSONChatFormat format : formats) {
                chatObject.put(format.getFormatString(), true);
            }
        }
    }

    @SuppressWarnings("unchecked")
	public void addExtra(JSONChatExtra extraObject) {
        if (!chatObject.containsKey("extra")) {
            chatObject.put("extra", new JSONArray());
        }
        JSONArray extra = (JSONArray) chatObject.get("extra");
        extra.add(extraObject.toJSON());
        chatObject.put("extra", extra);
    }

    public void sendToPlayer(Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ChatSerializer.a(chatObject.toJSONString())));
    }

	@Override
    public String toString() {
        return chatObject.toJSONString();
    }
}
