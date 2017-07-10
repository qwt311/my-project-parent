package com.project.service.impl;

import com.project.common.util.SendMailUtil;
import com.project.entity.PmBaseDate;
import com.project.service.BaseDataService;
import com.project.service.EmailService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by qiaowentao on 2016/11/3.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private BaseDataService baseDataService;

    @Override
    public void sendEmail(String email, String subject, String content) {
        DBContextHolder.setDataSource("dataSourceOne");
        PmBaseDate baseDate = new PmBaseDate();
        baseDate.setDicType("mailserver");
        List<PmBaseDate> baseDateList = baseDataService.selectBaseDataList(baseDate);
        if(baseDateList != null && baseDateList.size() > 0){
            for(PmBaseDate pmBaseDate:baseDateList){
                if("sendmail".equals(pmBaseDate.getDicKey())){
                    SendMailUtil.mailAddress = pmBaseDate.getDicValue();
                }else if("username".equals(pmBaseDate.getDicKey())){
                    SendMailUtil.mailCount = pmBaseDate.getDicValue();
                }else if("password".equals(pmBaseDate.getDicKey())){
                    SendMailUtil.mailPassword = pmBaseDate.getDicValue();
                }else if("host".equals(pmBaseDate.getDicKey())){
                    SendMailUtil.mailServer = pmBaseDate.getDicValue();
                }
            }
            SendMailUtil.sendMail(email,subject,content);
        }
    }
}
