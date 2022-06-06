package com.github.niefy.modules.wx.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.github.niefy.modules.wx.dao.MsgReplyRuleMapper;
import com.github.niefy.modules.wx.entity.MsgReplyRule;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序发送模板消息demo
 */
@SpringBootTest
class TemplateMsgServiceTest {
//    @Autowired
//    TemplateMsgService templateMsgService;
    @Autowired
    MsgReplyRuleMapper msgReplyRuleMapper;
    /**
     * 发送模板消息给用户
     * 添加消息模板指引：https://kf.qq.com/faq/170209E3InyI170209nIF7RJ.html
     * 示例消息模板为：{{first.DATA}} ↵商品名称：{{keyword1.DATA}} ↵购买时间：{{keyword2.DATA}} ↵{{remark.DATA}}
     */
    @Test
    void sendTemplateMsg() {
        List<MsgReplyRule> msgReplyRules = msgReplyRuleMapper.selectList(
                new QueryWrapper<MsgReplyRule>()
                        .eq("status", 1)
                        .eq("match_value", "情·冰封一世")
                        .orderByDesc("priority"));
        System.out.println(1);
    }
}
