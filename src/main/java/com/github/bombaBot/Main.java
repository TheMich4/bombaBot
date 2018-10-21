package com.github.bombaBot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {



    public static void main(String[] args) {
        String apiToken = "NTAzNTM1OTE3OTc4NDg0NzM2.Dq36hA.zQb_d30DUiTXf8uLNIR6pGkD-aY";
        DiscordApi api = new DiscordApiBuilder().setToken(apiToken).login().join();
        System.out.println("Logged in!");
        System.out.println(api.createBotInvite());
    }
}
