package com.github.bombaBot;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.List;
import java.util.Set;

public class Rank {

    public Set<LeaguePosition> leaguePositions;
    public LeaguePosition soloPosition;

    public Rank(List<CurrentGameParticipant> players, RiotApi riotApi) {

        for (int i = 0; i < players.size(); i++) {
            String summName = players.get(i).getSummonerName();
//            System.out.println(summName);
            try {
                long summId = riotApi.getSummonerByName(Platform.EUNE, summName).getId();

                leaguePositions = riotApi.getLeaguePositionsBySummonerId(Platform.EUNE, summId);

                for (LeaguePosition leaguePosition : leaguePositions) {
                    if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_SOLO_5x5.name())) {
                        soloPosition = leaguePosition;
                    }
                }
                System.out.println(soloPosition.getTier() + " " + soloPosition.getRank());

            } catch (RiotApiException e) {
                e.printStackTrace();
            }
        }
    }
}
