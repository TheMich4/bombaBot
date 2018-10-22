package com.github.bombaBot;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
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
        LiveGame liveGame = null;

        try {
            liveGame = new LiveGame(messageString);
        }
        catch (RiotApiException e) {
            e.printStackTrace();
        }


        new MessageBuilder()
                .setEmbed(new EmbedBuilder()
                        .setTitle("**Blue Team**")
                        .setColor(Color.decode("#105187"))
                        .addInlineField(liveGame.participants.get(0).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(1).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(2).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(3).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(4).getSummonerName(), "RANK")
                        .addInlineField("placeholder", "placeholder"))
                .send(event.getChannel());

        new MessageBuilder()
                .setEmbed(new EmbedBuilder()
                        .setTitle("**Red Team**")
                        .setColor(Color.decode("#D02C25"))
                        .addInlineField(liveGame.participants.get(5).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(6).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(7).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(8).getSummonerName(), "RANK")
                        .addInlineField(liveGame.participants.get(9).getSummonerName(), "RANK")
                        .addInlineField("placeholder", "placeholder"))
                .send(event.getChannel());
    }
}
