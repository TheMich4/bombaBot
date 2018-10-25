package com.github.bombaBot;

import net.rithms.riot.api.RiotApiException;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class Listener implements MessageCreateListener {

    Message message = null;
    MessageCreateEvent event = null;
    String messageString = "";

    @Override
    public void onMessageCreate(MessageCreateEvent createEvent) {

        event = createEvent;
        message = event.getMessage();

        messageString = message.getContent();

//        System.out.println(messageString.substring(0,5));
//        System.out.println(messageString.substring(6));

        if (message.getAuthor().getDisplayName().equals("Witles")) {
            witlesMessage();
        }

        if (messageString.substring(0, 5).equals("!help")) {
            displayHelpMessage();
        } else if (messageString.substring(0, 5).equals("!game")) {
            displayGameBySummoner();
        } else if (messageString.substring(0, 5).equals("!rank")) {
            displayRankBySummoner();
        }
    }

    private void displayHelpMessage() {

        String commands = ("**!help** - Displays all commands \n"
                + "**!game** - Displays current game of a given summoner \n"
                + "**!rank** - Displays rank of a fiven summoner");

        new MessageBuilder()
                .setEmbed(new EmbedBuilder()
                        .setTitle("Available commands")
                        .setDescription(commands))
                .send(event.getChannel());
    }

    private void displayGameBySummoner() {

        LiveGame liveGame = null;

        String summName = messageString.substring(6);

        try {
            liveGame = new LiveGame(summName);
        } catch (RiotApiException e) {
            e.printStackTrace();
        }

        Rank rank = new Rank(liveGame.participants, liveGame.riotApi);

        new MessageBuilder()
                .setEmbed(new EmbedBuilder()
                                .setTitle("**Blue Team**")
                                .setColor(Color.decode("#105187"))
                                .addInlineField(liveGame.participants.get(0).getSummonerName(), rank.getRankById(0))
                                .addInlineField(liveGame.participants.get(1).getSummonerName(), rank.getRankById(1))
                                .addInlineField(liveGame.participants.get(2).getSummonerName(), rank.getRankById(2))
                                .addInlineField(liveGame.participants.get(3).getSummonerName(), rank.getRankById(3))
                                .addInlineField(liveGame.participants.get(4).getSummonerName(), rank.getRankById(4))
//                        .addInlineField("placeholder", "placeholder")
                ).send(event.getChannel());

        new MessageBuilder()
                .setEmbed(new EmbedBuilder()
                                .setTitle("**Red Team**")
                                .setColor(Color.decode("#D02C25"))
                                .addInlineField(liveGame.participants.get(5).getSummonerName(), rank.getRankById(5))
                                .addInlineField(liveGame.participants.get(6).getSummonerName(), rank.getRankById(6))
                                .addInlineField(liveGame.participants.get(7).getSummonerName(), rank.getRankById(7))
                                .addInlineField(liveGame.participants.get(8).getSummonerName(), rank.getRankById(8))
                                .addInlineField(liveGame.participants.get(9).getSummonerName(), rank.getRankById(9))
//                        .addInlineField("placeholder", "placeholder")
                ).send(event.getChannel());
    }

    private void displayRankBySummoner() {

        String summName = messageString.substring(6);

        Rank rank = new Rank(Main.riotApi);

        try {
            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Rank of " + summName)
                            .setColor(Color.pink)
                            .setDescription(rank.getRankBySummonerName(summName)))
                    .send(event.getChannel());
        } catch (RiotApiException e) {
            e.printStackTrace();
        }
    }

    private void witlesMessage() {
        new MessageBuilder()
                    .setTts(true)
                    .setContent("Czas najwyzszy zebys zamknal morde")
                    .send(event.getChannel());
    }
}
