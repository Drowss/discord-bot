package com.drow.cmdmanager;

import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlashCommands extends ListenerAdapter {

    private List<ICommand> commands;

    public SlashCommands(List<ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for (ICommand cmd : commands) {
            if (cmd.getName().equals(event.getName())) {
                cmd.execute(event);
                return;
            }
        }
    }

    public static EmbedBuilder createEmbed(String imageUrl, String footerText, String title, String description) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setImage(imageUrl);
        embed.setFooter(footerText, "https://media.discordapp.net/attachments/423257150593433610/1252484624723214366/image.png?ex=667262b4&is=66711134&hm=ce171a91dfbe9c6d5a4456d928b78bfee1eb0bc8763b297d402e5fdc43f1c2c8&=&format=webp&quality=lossless");
        embed.setColor(Color.ORANGE);
        embed.setTimestamp(Instant.now());
        embed.setTitle(title);
        if (description != null) {
            embed.setDescription(description);
        }
        return embed;
    }
}
