package me.grimy.api.chat;

import com.google.common.collect.Lists;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;

import java.util.List;

public class ChatElement {

    private TextComponent textComponent;

    private ChatElement(String text) {
        this.textComponent = new TextComponent(text);
    }

    public static ChatElement of(String string) {
        return new ChatElement(string);
    }

    private List<String> colors(String string, List<String> list) {
        String other = string.replace(ChatColor.stripColor(string), "");
        string = string.replace(other, "");

        boolean again = string.length() > 2;

        String result = "";
        int length = string.length();

        for(int index = length - 1; index > -1; --index) {
            char section = string.charAt(index);
            if (section == 167 && index < length - 1) {
                char c = string.charAt(index + 1);
                ChatColor color = ChatColor.getByChar(c);
                if (color != null) {
                    result = color.toString() + result;
                    if (color.equals(ChatColor.RESET)) {
                        break;
                    }
                }
            }
        }
        list.add(result);

        if (again) {
            return colors(string.replace(result, ""), list);
        }
        return list;
    }

    public ChatElement color(String string) {
        List<String> colors = colors(string, Lists.newArrayList());

        colors.forEach(s -> color(ChatColor.getByChar(s.toCharArray()[1])));
        return this;
    }

    public ChatElement color(ChatColor chatColor) {
        if (chatColor == ChatColor.BOLD) {
            this.textComponent.setBold(true);
            return this;
        }

        if (chatColor == ChatColor.ITALIC) {
            this.textComponent.setItalic(true);
            return this;
        }

        if (chatColor == ChatColor.UNDERLINE) {
            this.textComponent.setUnderlined(true);
            return this;
        }

        if (chatColor == ChatColor.STRIKETHROUGH) {
            this.textComponent.setStrikethrough(true);
            return this;
        }

        if (chatColor == ChatColor.MAGIC) {
            this.textComponent.setObfuscated(true);
            return this;
        }

        textComponent.setColor(chatColor);
        return this;
    }

    public ChatElement hover(HoverEvent hoverEvent) {
        this.textComponent.setHoverEvent(hoverEvent);
        return this;
    }

    public ChatElement click(ClickEvent clickEvent) {
        this.textComponent.setClickEvent(clickEvent);
        return this;
    }

    public ChatElement append(String string) {
        this.textComponent.addExtra(string);
        return this;
    }

    public ChatElement replace(String old, String replacement) {
        this.textComponent.setText(this.textComponent.getText().replace(old, replacement));
        return this;
    }

    public ChatElement append(ChatElement chatElement) {
        this.textComponent.addExtra(chatElement.textComponent);
        return this;
    }

    public String legacy() {
        return textComponent.toLegacyText();
    }

    public void send(Player player) {
        player.spigot().sendMessage(textComponent);
    }
}
