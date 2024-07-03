package com.drow.events;

import com.drow.cmdmanager.SlashCommands;
import com.drow.database.PostgresDb;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;
import java.util.Random;

public class Punch implements ICommand {

    private List<String> urls = List.of("https://i.pinimg.com/originals/ac/6d/a3/ac6da3c5a4ccd320af2b5105a32c5f95.gif",
            "https://gifdb.com/images/high/stewie-punching-brian-wheres-my-money-660sluzfnrsugbzj.gif",
            "https://i.pinimg.com/originals/41/79/08/41790876f5f694450c9653dcbef629a5.gif",
            "https://i.pinimg.com/originals/0f/ac/85/0fac85ef4e7d5c41951af7de21943e12.gif");

    @Override
    public String getName() {
        return "golpe";
    }

    @Override
    public String getDescription() {
        return "Golpea a alguien";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(
                OptionType.USER,
                "usuario",
                "A quién vas a golpear",
                true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String violentUser = event.getMember().getId();
        String userHit = event.getOption("usuario", OptionMapping::getAsUser).getId();
        PostgresDb.insertData(event.getMember().getId(), userHit);

        int hits = PostgresDb.selectHit(violentUser, userHit);
        String user = event.getOption("usuario", OptionMapping::getAsUser).getEffectiveName();
        String hitText = hits == 1 ? " vez a " : " veces a ";

        EmbedBuilder embed = SlashCommands.createEmbed(
                urls.get(randomInt()),
                "No sé que poner aquí ",
                event.getMember().getEffectiveName() + " ha golpeado " + hits + hitText + user,
                "putx"
        );
        event.replyEmbeds(embed.build()).queue();
    }

    public Integer randomInt() {
        Random random = new Random();
        return random.nextInt(0, urls.size() - 1);
    }
}
