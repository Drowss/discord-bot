package com.drow.events;

import com.drow.cmdmanager.SlashCommands;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;
import java.util.Random;

public class Greet implements ICommand {

    private List<String> urls = List.of(
            "https://media4.giphy.com/media/tOmDGjivsVTNK/200w.gif?cid=6c09b9529o2bok75b5f49oufmiz5nmoezmk1chtlrwjwogck&ep=v1_gifs_search&rid=200w.gif&ct=g",
            "https://64.media.tumblr.com/0ab7113fc00465b0cc47a3f6c880add7/tumblr_o3a3331jfI1v9f6hno1_540.gif",
            "https://i.pinimg.com/originals/58/99/1f/58991fca6128f096336cfb9f3013aa4f.gif",
            "https://i.pinimg.com/originals/58/57/25/585725b5a8236d61e5490b9bd5cf9b64.gif"
            );

    @Override
    public String getName() {
        return "saludar";
    }

    @Override
    public String getDescription() {
        return "Responde un saludo con ositos";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = SlashCommands.createEmbed(
                    urls.get(randomInt()),
                    event.getMember().getEffectiveName() + " piensa que Drow es el mejor",
                    event.getMember().getEffectiveName() + " saluda",
                    "Tengan un bonito día :D"
            );
            event.replyEmbeds(embed.build()).queue();
    }

    public Integer randomInt() {
        Random random = new Random();
        return random.nextInt(0, urls.size() - 1);
    }
}
