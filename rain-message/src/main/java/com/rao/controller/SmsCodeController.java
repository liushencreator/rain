package com.rao.controller;

import com.rao.annotation.BeanValid;
import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.pojo.dto.SmsSendDTO;
import com.rao.service.SmsCodeService;
import com.rao.util.result.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信验证码
 * @author raojing
 * @date 2020/1/6 15:41
 */
@RestController
@RequestMapping("/sms")
public class SmsCodeController {
    
    @Resource
    private SmsCodeService smsCodeService;

    /**
     * 发送短信验证码
     * @param smsSendDTO
     * @return
     */
    @PostMapping("/send_code")
    public ResultMessage<String> sendCode(@BeanValid @RequestBody SmsSendDTO smsSendDTO){
        String msgCode = smsCodeService.sendCode(smsSendDTO);
        return ResultMessage.success(msgCode).message("短信发送成功");
    }
    
}
