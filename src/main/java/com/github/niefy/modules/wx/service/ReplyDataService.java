package com.github.niefy.modules.wx.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.niefy.common.utils.MatchUtils;
import com.github.niefy.modules.wx.dao.MsgReplyRuleMapper;
import com.github.niefy.modules.wx.entity.MsgReplyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 *
 */
@Service
public class ReplyDataService {
    @Autowired
    MsgReplyRuleMapper msgReplyRuleMapper;

    private static final String NUM_ERROR_MSG = "编号位数有误.\n标准编号为3位,4位,11位,15位。";

    private String matchValue(String key) {
        List<MsgReplyRule> msgReplyRules = msgReplyRuleMapper.selectList(
                new QueryWrapper<MsgReplyRule>()
                        .eq("status", 1)
                        .eq("match_value", key)
                        .orderByDesc("priority"));
        if (CollectionUtils.isEmpty(msgReplyRules)) {
            return "";
        }
        return msgReplyRules.get(0).getReplyContent();
    }

    public String getWxReply(String key) {
        //一.全字母或数字或字母数字组合，精确匹配
        if (MatchUtils.isLetter(key) || MatchUtils.isNum(key) || MatchUtils.isNumLetter(key)) {
            if (key.length() != 3 && key.length() != 4 && key.length() != 11 && key.length() != 15) {
                return NUM_ERROR_MSG;
            }
            //判断组合查询
            if (MatchUtils.isCombination(key)) {
                return combinatorial(key);
            }
            return matchValue(key);
        } else {
            return "";
        }
    }


    /**
     * 商户编号组合查询
     *
     * @param key
     * @return
     */
    public String combinatorial(String key) {
        //1.解析key1
        String key1 = key.substring(0, 3);
        StringBuilder sb = new StringBuilder("收单机构：");
        String shoudan = matchValue(key1);
        shoudan = org.apache.commons.lang3.StringUtils.isBlank(shoudan) ? "未查询到该收单机构" : shoudan;
        sb.append(shoudan).append("\n\n");
        //2.解析key2
        String key2 = key.substring(3, 7);
        String xiaofei = matchValue(key2);
        xiaofei = org.apache.commons.lang3.StringUtils.isBlank(xiaofei) ? "未查询到该消费地区" : xiaofei;
        sb.append("消费地区：").append(xiaofei).append("\n\n");

        //3.解析key3
        String key3 = key.substring(7, 11);
        String shanghu = matchValue(key3);
        shanghu = org.apache.commons.lang3.StringUtils.isBlank(shanghu) ? "未查询到该商户类型" : shanghu;
        sb.append("商户类型：").append(shanghu);
        return sb.toString();
    }
}
