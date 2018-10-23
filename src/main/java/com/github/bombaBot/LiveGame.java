package com.github.bombaBot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.champion.dto.ChampionList;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.constant.Platform;

import java.util.List;

public class LiveGame {

    List<CurrentGameParticipant> participants = null;

    ApiConfig config = new ApiConfig().setKey("RGAPI-0a202937-6b8d-4024-b63b-d420480bfb49");
    RiotApi riotApi = new RiotApi(config);

    public LiveGame(String inputName) throws RiotApiException {

        Platform platform = Platform.EUNE;
        long summonerID = riotApi.getSummonerByName(platform, inputName).getId();


        participants = riotApi.getActiveGameBySummoner(platform, summonerID).getParticipants();

        System.out.println(participants);

//        Rank rank = new Rank(participants, riotApi);

//        for (CurrentGameParticipant player : participants) {
//            System.out.println(player.getChampionId() + ", " + player.getSummonerName());
//        }

    }
}
