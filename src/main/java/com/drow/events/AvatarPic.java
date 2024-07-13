package com.drow.events;

import com.drow.cmdmanager.SlashCommands;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.ImageProxy;

import java.util.List;
import java.util.Optional;

public class AvatarPic implements ICommand {


    @Override
    public String getName() {
        return "avatar";
    }

    @Override
    public String getDescription() {
        return "Muestra el avatar de un usuario";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(
                OptionType.USER,
                "usuario",
                "Muestra el avatar de un usuario",
                true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Optional<String> avatarUrl = Optional.ofNullable(event.getOption("usuario"))
                .map(OptionMapping::getAsUser)
                .map(User::getAvatar)
                .map(image -> image.getUrl(300));
        String user = event.getOption("usuario").getAsUser().getName();
        avatarUrl.ifPresentOrElse(
                url -> event.replyEmbeds(SlashCommands.createEmbed(url, "Powered by Ositos Cariñositos", "Foto de " + user, "")
                        .build()).queue(),
                () -> event.reply("No se encontró el avatar").queue()
        );
    }
}
