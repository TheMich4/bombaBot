package com.github.bombaBot;

import net.rithms.riot.api.RiotApiException;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class Listener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();

        String messageString = message.getContent();

//        System.out.println(messageString.substring(0,5));
//        System.out.println(messageString.substring(6));

        if (messageString.substring(0, 5).equals("!game")) {
            displayGameBySummoner(event, messageString);
        }
        else if (messageString.substring(0,5).equals("!rank")) {
            displayRankBySummoner(event, messageString);
        }
    }

    private void displayGameBySummoner(MessageCreateEvent event, String messageString) {
        System.out.println("WENT INTO LOOP !game - " + messageString.substring(6));

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

    private void displayRankBySummoner(MessageCreateEvent event, String messageString) {
        System.out.println("WENT INTO LOOP !ramk - " + messageString.substring(6));

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
}
