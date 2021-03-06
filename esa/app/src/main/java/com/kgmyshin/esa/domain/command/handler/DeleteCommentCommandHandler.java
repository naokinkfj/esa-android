/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.domain.command.handler;

import com.kgmyshin.esa.domain.command.DeleteCommentCommand;
import com.kgmyshin.esa.domain.repository.CommentRepository;
import com.kgmyshin.esa.domain.repository.CommentRepositoryFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;

public class DeleteCommentCommandHandler extends CommandHandler<DeleteCommentCommand> {

    private CommentRepositoryFactory repositoryFactory;
    private EventBus eventBus;

    @Inject
    public DeleteCommentCommandHandler(CommentRepositoryFactory repositoryFactory, EventBus eventBus) {
        this.repositoryFactory = repositoryFactory;
        this.eventBus = eventBus;
    }

    @Override
    public void execute(DeleteCommentCommand command) {
        CommentRepository repository = repositoryFactory.create(command.getTeamName(), command.getPostNumber());
        try {
            repository.delete(command.getId());
            eventBus.postSticky(new DeleteCommentCommand.CommentDeletedEvent());
        } catch (IOException e) {
            e.printStackTrace();
            eventBus.postSticky(new DeleteCommentCommand.FailedDeleteCommentEvent());
        }
    }
}
