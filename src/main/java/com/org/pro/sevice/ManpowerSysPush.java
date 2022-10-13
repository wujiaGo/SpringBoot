package com.org.pro.sevice;

import com.org.pojo.Flow;
import com.org.pro.FlowPush;
import org.springframework.stereotype.Component;

@Component
public class ManpowerSysPush implements FlowPush {

    @Override
    public String push(Flow flow) {
        return "推送人力系统成功！";
    }

}
