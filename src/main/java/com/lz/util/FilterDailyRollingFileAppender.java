package com.lz.util;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class FilterDailyRollingFileAppender extends DailyRollingFileAppender {
    
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {  
          //只判断是否相等，而不判断优先级   
        return this.getThreshold().equals(priority);  
    }  
}