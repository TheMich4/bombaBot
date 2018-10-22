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

    public LiveGame(String inputName) throws RiotApiException {

        ApiConfig config = new ApiConfig().setKey("RGAPI-f04eda9a-7654-4e33-a004-cfbda55b43d6");
        RiotApi riotApi = new RiotApi(config);

        Platform platform = Platform.EUNE;
        long summonerID = riotApi.getSummonerByName(platform, inputName).getId();


        participants = riotApi.getActiveGameBySummoner(platform, summonerID).getParticipants();

        System.out.println(participants);

        Rank rank = new Rank(participants, riotApi);

//        for (CurrentGameParticipant player : participants) {
//            System.out.println(player.getChampionId() + ", " + player.getSummonerName());
//        }

    }
}
