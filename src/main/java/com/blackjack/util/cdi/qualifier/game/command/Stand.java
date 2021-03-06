package com.blackjack.util.cdi.qualifier.game.command;

import javax.inject.Qualifier;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface Stand {}
