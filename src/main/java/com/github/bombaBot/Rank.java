package com.github.bombaBot;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.league.dto.LeaguePosition;
import net.rithms.riot.api.endpoints.spectator.dto.CurrentGameParticipant;
import net.rithms.riot.constant.Platform;

import java.util.List;
import java.util.Set;

public class Rank {

    public Set<LeaguePosition> leaguePositions;
    public String[] soloPosition = new String[10];
    RiotApi riotApi;
    List<CurrentGameParticipant> participants;

    public Rank(List<CurrentGameParticipant> players, RiotApi api) {

        riotApi = api;

        for (int i = 0; i < players.size(); i++) {
            try {
                leaguePositions = riotApi.getLeaguePositionsBySummonerId(Platform.EUNE, players.get(i).getSummonerId());

                for (LeaguePosition leaguePosition : leaguePositions) {
                    if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_SOLO_5x5.name())) {
                        soloPosition[i] = leaguePosition.getTier() + " " + leaguePosition.getRank() + " - " + leaguePosition.getLeaguePoints() + " LP";
                        System.out.println(soloPosition[i]);
                    }
                }

            } catch (RiotApiException e) {
                e.printStackTrace();
            }
        }
    }

    public Rank(RiotApi api) {
        this.riotApi = api;
    }

    public String getRankById(int i) {
        return soloPosition[i];
    }

    public String getRankBySummonerName(String summonerName) throws RiotApiException {
        String rank = "";
        long summId = 0;

        Set<LeaguePosition> position;

        try {
            summId = riotApi.getSummonerByName(Platform.EUNE, summonerName).getId();
        } catch (RiotApiException e) {
            e.printStackTrace();
        }

        if (summId != 0) {
            position = riotApi.getLeaguePositionsBySummonerId(Platform.EUNE, summId);

            for (LeaguePosition leaguePosition : position) {
                if (leaguePosition.getQueueType().equals(LeagueQueue.RANKED_SOLO_5x5.name())) {
                    rank = leaguePosition.getTier() + " " + leaguePosition.getRank() + " - " + leaguePosition.getLeaguePoints() + " LP";
                    System.out.println(rank);
                }
            }
        }

        return rank;
    }

}
