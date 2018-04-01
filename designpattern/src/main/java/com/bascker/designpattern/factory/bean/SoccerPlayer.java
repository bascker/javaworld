package com.bascker.designpattern.factory.bean;

/**
 * Soccer Player
 *
 * @author bascker
 */
public class SoccerPlayer implements Player {

    @Override
    public String sport() {
        return "Soccer";
    }
}
