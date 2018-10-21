package com.github.bombaBot;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Platform;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

    public static void main(String[] args) throws RiotApiException {
        String apiToken = "NTAzNTM1OTE3OTc4NDg0NzM2.Dq36hA.zQb_d30DUiTXf8uLNIR6pGkD-aY";

        DiscordApi api = new DiscordApiBuilder().setToken(apiToken).login().join();
        System.out.println("Logged in!");
//        System.out.println(api.createBotInvite());

//        ApiConfig config = new ApiConfig().setKey("RGAPI-3097c00c-ff0c-4809-bbb8-86f5205cf36b");
//        RiotApi riotApi = new RiotApi(config);
//
//        Platform platform = Platform.EUNE;
//        long summonerID = riotApi.getSummonerByName(platform, "DYCZKOBOMBA").getId();
//
//        System.out.println(summonerID);

        api.addListener(new Listener());

//        LiveGame liveGame = new LiveGame("Inspectrium");
    }
}
