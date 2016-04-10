/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.DeleteCommentCommand;
import com.kgmyshin.esa.repository.CommentRepository;
import com.kgmyshin.esa.repository.CommentRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class DeleteCommentCommandHandler extends CommandHandler<DeleteCommentCommand> {

    @Inject
    CommentRepositoryFactory repositoryFactory;
    @Inject
    @Named("event")
    EventBus eventBus;

    @Override
    public void execute(DeleteCommentCommand command) {
        CommentRepository repository = repositoryFactory.create(command.getTeamName(), command.getPostNumber());
        try {
            repository.delete(command.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}