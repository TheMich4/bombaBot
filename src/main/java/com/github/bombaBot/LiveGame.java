package com.github.bombaBot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.constant.Platform;

import java.util.List;

public class LiveGame {

    List<CurrentGameParticipant> participants = null;

    public LiveGame(String inputName) throws RiotApiException {

        ApiConfig config = new ApiConfig().setKey("RGAPI-3097c00c-ff0c-4809-bbb8-86f5205cf36b");
        RiotApi api = new RiotApi(config);

        Platform platform = Platform.EUNE;
        long summonerID = api.getSummonerByName(platform, inputName).getId();


        participants = api.getActiveGameBySummoner(platform, summonerID).getParticipants();

        System.out.println(participants);

        for (CurrentGameParticipant player : participants) {
            System.out.println(player.getChampionId() + ", " + player.getSummonerName());
        }



    }
}
