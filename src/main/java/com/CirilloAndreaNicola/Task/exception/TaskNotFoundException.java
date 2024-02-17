package com.CirilloAndreaNicola.Task.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String messaggio) {
        super(messaggio);
    }
}

