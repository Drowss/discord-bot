package com.drow.events;

import com.drow.cmdmanager.SlashCommands;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Question implements ICommand {

    @Override
    public String getName() {
        return "preguntar";
    }

    @Override
    public String getDescription() {
        return "Busca qué osito le preguntó a otro osito";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(
                OptionType.USER,
                "usuario",
                "A quién le preguntó el osito",
                true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String user = event.getOption("usuario", OptionMapping::getAsUser).getEffectiveName();
            EmbedBuilder embed = SlashCommands.createEmbed(
                    "https://64.media.tumblr.com/3251d5c158ca418f6beb50df3e09c184/tumblr_ocsmvhk8Zq1v9f6hno1_540.gif",
                    "Buscando quién preguntó desde ",
                    event.getMember().getEffectiveName() + " busca quien le preguntó a " + user,
                    "¿?"
            );
            event.replyEmbeds(embed.build()).queue();
    }
}
