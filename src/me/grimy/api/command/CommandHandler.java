package me.grimy.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandHandler {

	String name();
	
	String permission() default "None";
	
	String description() default "None";
	
	String usage() default "None";
	
	String[] aliases() default "None";
}
