package com.vinfai.distributed.sentinel.flow;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlowApi {

    public static final String FLOWQPS_API = "flowqps_api";

    @RequestMapping(path = "/flow/qps")
    public String flowQps() {
        String name = "hello";
        Entry entry = null;
        try {
             entry = SphU.entry(FLOWQPS_API);
             name +=System.currentTimeMillis();
        } catch (BlockException e) {
            e.printStackTrace();
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return name;
    }

    @Bean
    public void init() {
        initFlowRule();
    }

    public void initFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(FLOWQPS_API);
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        FlowRuleManager.loadRules();
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

    }

}
