package com.github.bombaBot;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.user.UserChangeNicknameEvent;
import org.javacord.api.listener.user.UserChangeNicknameListener;

public class NicknameChangeListener implements UserChangeNicknameListener {


    @Override
    public void onUserChangeNickname(UserChangeNicknameEvent event) {
        
        String message = "Na chuj zmieniasz ten nick?";

        new MessageBuilder()
                .setContent(message)
                .send(event.getUser());

    }
}
