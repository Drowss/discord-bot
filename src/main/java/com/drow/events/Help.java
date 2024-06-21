package com.drow.events;

import com.drow.cmdmanager.SlashCommands;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.List;

public class Help implements ICommand {

    private List<ICommand> commands;

    @Override
    public String getName() {
        return "ayuda";
    }

    @Override
    public String getDescription() {
        return "Muestra los comandos disponibles";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Comandos disponibles");
        embed.setDescription("Estos son los comandos disponibles para usar");
        for (ICommand command : commands) {
            embed.addField(command.getName(), command.getDescription(), false);
        }
        embed.setFooter("Drow Bot", event.getJDA().getSelfUser().getAvatarUrl());
        embed.setColor(Color.ORANGE);
        event.replyEmbeds(embed.build()).queue();
    }

    public void setCommands(List<ICommand> commands) {
        this.commands = commands;
    }
}
