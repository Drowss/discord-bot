package com.drow;


import com.drow.cmdmanager.SlashCommands;
import com.drow.database.PostgresDb;
import com.drow.events.*;
import com.drow.interfaces.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        PostgresDb.initDb();

        List<ICommand> commandList = List.of(
                new Question(),
                new Greet(),
                new Help(),
                new Say(),
                new Punch()
        );

        for (ICommand command : commandList) {
            if (command instanceof Help) {
                ((Help) command).setCommands(commandList);
            }
        }

        JDA jda = JDABuilder.createDefault(System.getenv("bot_token"))
                .setActivity(Activity.watching("Planeta Juan"))
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(new SlashCommands(commandList))
                .build();
        jda.awaitReady();

        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(
                Commands.slash("decir", "Dí algo xd")
                        .addOption(OptionType.STRING, "mensaje", "Dí algo xd", false),
                Commands.slash("saludar", "Responde un saludo con ositos bonitos"),
                Commands.slash("preguntar", "Busca qué osito le preguntó a otro osito")
                        .addOption(OptionType.USER, "usuario", "A quién le preguntó el osito", true),
                Commands.slash("ayuda", "Muestra todos los comandos disponibles"),
                Commands.slash("golpe", "Golpea a alguien")
                        .addOption(OptionType.USER, "usuario", "A quién vas a golpear", true)
        ).queue();
    }
}