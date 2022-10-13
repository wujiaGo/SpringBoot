package com.org.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.mapper.WujiaMapper;
import com.org.pojo.Flow;
import com.org.pojo.Wujia;
import com.org.pro.FlowPush;
import com.org.util.SpringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ResponseBody
@RestController
@RequestMapping("/wujia")
public class WujiaController {

    WujiaMapper wujiaMapper;

    public WujiaMapper getWujiaMapper() {
        if (wujiaMapper == null) {
            wujiaMapper = (WujiaMapper) SpringUtils.getBean("wujiaMapper");
        }
        return wujiaMapper;
    }

    @PostMapping("/push")
    public String push(@RequestBody Map map) {
        FlowPush flowPush = SpringUtils.getBean((String) map.get("Flow"));
        String a = "1";
        System.out.println(a);
        return flowPush.push(new Flow());
    }

    @GetMapping("/select")
    public Map selectWujia(@RequestBody Map<String, String> selectWhere) {
        WujiaMapper mapper = getWujiaMapper();
        Map result = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (selectWhere.size() != 0) {
            queryWrapper.allEq(selectWhere);
        }
        List<Wujia> wujias = mapper.selectList(queryWrapper);
        result.put("0", "成功");
        result.put("data", wujias);
        return result;
    }

    @GetMapping("/insert")
    public Map insertWujia(@RequestBody Wujia wujia) {
        WujiaMapper mapper = getWujiaMapper();
        Map result = new HashMap();
        int id = mapper.insert(wujia);
        result.put("0", "成功");
        result.put("data", id);
        return result;
    }

    @GetMapping("/update")
    public Map updetaWujia(@RequestBody Wujia wujia) {
        WujiaMapper mapper = getWujiaMapper();
        Map result = new HashMap();
        int id = mapper.updateById(wujia);
        result.put("0", "成功");
        result.put("data", id);
        return result;

    }

    @GetMapping("/page")
    public Map pageWujia(@RequestBody Map map) {
        WujiaMapper mapper = getWujiaMapper();
        String current = map.get("current").toString();
        String size = map.get("size").toString();
        Page<Wujia> page = new Page<>(Integer.parseInt(current), Integer.parseInt(size));//开启拦截器后，会注册一个page对象  当前页，每页条数
        //方法源码：   <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        mapper.selectPage(page, null);
        Map result = new HashMap();
        result.put("0", "成功");
        result.put("data", page.getRecords());
        return result;
    }

    @GetMapping("/delete")
    public Map deleteWujia(@RequestBody List<String> ids) {
        WujiaMapper mapper = getWujiaMapper();
        Map result = new HashMap();
        int sum = 0;
        for (String id : ids) {
            sum += mapper.deleteById(id);
        }
        if (sum == ids.size()) {
            result.put("0", "成功");
            result.put("data", sum);
        } else {
            result.put("1", "失败");
            result.put("data", 0);
        }
        return result;
    }

}
