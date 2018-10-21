package com.github.bombaBot;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Listener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();

        String messageString = message.getContent();
        LiveGame liveGame = null;

        try {
            liveGame = new LiveGame(messageString);
        } catch (RiotApiException e) {
            e.printStackTrace();
        }


        String output = "```";

        for (CurrentGameParticipant participant : liveGame.participants) {
            output += participant.getSummonerName() + "\n";
        }

        output += "```";



        event.getChannel().sendMessage(output);

    }

}
