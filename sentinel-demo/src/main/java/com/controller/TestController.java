package com.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ：Puyb
 * @date ：Created in 2023/1/10 17:23
 * @description：
 */
@RestController
@RequestMapping()
public class TestController {
    @GetMapping("/user")
    @SentinelResource(value = "User", blockHandler = "blockHandlerForGetUser")
    public String getUser(){
        return "11111";
    }
    public String blockHandlerForGetUser(BlockException e){
        e.printStackTrace();
        return "降级";
    }

    @PostConstruct
    private static void init(){
        ArrayList<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("User");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

    }
}
