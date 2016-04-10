/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/03.
 */

package com.kgmyshin.esa.command.handler;

import com.kgmyshin.esa.command.Command;

public abstract class CommandHandler<T extends Command> {

    abstract public void execute(T t);

}
