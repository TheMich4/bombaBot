package com.github.bombaBot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.static_data.dto.ChampionList;
import net.rithms.riot.constant.Platform;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static RiotApi riotApi = null;

    public static void main(String[] args) throws RiotApiException {

        // DICORD API Settings
        String apiToken = "NTAzNTM1OTE3OTc4NDg0NzM2.Dq36hA.zQb_d30DUiTXf8uLNIR6pGkD-aY";
        DiscordApi api = new DiscordApiBuilder().setToken(apiToken).login().join();
        System.out.println("Logged in!");
        System.out.println(api.createBotInvite());

        // RIOT API Settings
        ApiConfig config = new ApiConfig().setKey("RGAPI-0a202937-6b8d-4024-b63b-d420480bfb49");
        riotApi = new RiotApi(config);

        Platform platform = Platform.EUNE;
//        long summonerID = riotApi.getSummonerByName(platform, "DYCZKOBOMBA").getId();
//
//        System.out.println(summonerID);

        api.addListener(new Listener());
        api.addListener(new NicknameChangeListener());

//        LiveGame liveGame = new LiveGame("Inspectrium");

//        ChampionList championList = riotApi.getDataChampionList(platform);

//        System.out.println(championList);
    }
}
