package com.drow.events;

import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Say implements ICommand {

        @Override
        public String getName() {
            return "decir";
        }

        @Override
        public String getDescription() {
            return "Dí algo xd";
        }

        @Override
        public List<OptionData> getOptions() {
            return List.of(new OptionData(
                    OptionType.STRING,
                    "mensaje",
                    "Dí algo xd",
                    true));
        }

        @Override
        public void execute(SlashCommandInteractionEvent event) {
            String message = event.getOption("mensaje").getAsString();
            event.reply(message).queue();
        }
}
