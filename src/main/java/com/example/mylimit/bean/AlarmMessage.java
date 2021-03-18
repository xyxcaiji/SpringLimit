package com.example.mylimit.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AlarmMessage {
    private int scopeId;
    private String name;
    private int id0;
    private int id1;
    private String alarmMessage;
    private long startTime;


}
