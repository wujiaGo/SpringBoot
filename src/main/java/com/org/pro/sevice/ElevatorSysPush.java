package com.org.pro.sevice;

import com.org.pojo.Flow;
import com.org.pro.FlowPush;
import org.springframework.stereotype.Component;

@Component
public class ElevatorSysPush implements FlowPush {

    @Override
    public String push(Flow flow) {
        return "推送电梯系统成功！";
    }

}
