package com.project.web.controller;

import com.project.common.util.ResponsesDTO;
import com.project.common.util.excel.ExcelException;
import com.project.web.util.ExcelUtils;
import com.project.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by qiaowentao on 2016/11/14.
 */
@Controller
@RequestMapping(value="/resource")
public class ResourceController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);


    @RequestMapping(value="/index",method = RequestMethod.GET)
    public ModelAndView toResourceIndex(ModelAndView model, HttpServletRequest request, HttpServletResponse response){

        model.setViewName("/resource/resourceIndex");
        return model;
    }

    @ResponseBody
    @RequestMapping(value="/import/excel",method = {RequestMethod.POST}, produces = "application/json;charset=utf-8")
    public ResponsesDTO importExcel(@RequestParam("fileToUpload")MultipartFile multipartFile, ModelAndView model,
                                    HttpServletRequest request, HttpServletResponse response){
        ResponsesDTO responsesDTO = new ResponsesDTO();
        responsesDTO.setCode(0);
        try {
            InputStream in = multipartFile.getInputStream();
            Workbook book = WorkbookFactory.create(in);
            LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("手机号","phone");
            hashMap.put("昵称","nicheng");
            hashMap.put("性别","sexStr");
            hashMap.put("账户余额","monetStr");
            hashMap.put("用户名","username");
            hashMap.put("地址","address");
            hashMap.put("邮箱","email");
            hashMap.put("QQ","qq");
            //String[] uniqueFields = new String[0];
            List<User> list = ExcelUtils.excelImport(User.class, book,hashMap);
            logger.info(list.toString());

            responsesDTO.setAttach("导入完成");
        }catch (ExcelException exe){
            responsesDTO.setCode(1);
            responsesDTO.setAttach(exe.getMessage());
        }catch (Exception e){
            responsesDTO.setCode(1);
            responsesDTO.setAttach("导入失败");
        }

        return responsesDTO;
    }

}
