package com.dynasty.blog.user.utils;

import com.kgkt.tust.common.utils.R;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @Description: 校验工具类
 * @Author: 王朝
 * @Date: 2023/12/15 16:47
 */
public class MyBindResultUtils {
  public static  List dataValid(BindingResult bindingResult){
    if (bindingResult.hasErrors()){
      List<ObjectError> allErrors = bindingResult.getAllErrors();
      ArrayList<String> DataResList = new ArrayList<>();
      for (ObjectError fieldError : allErrors) {
        String defaultMessage = fieldError.getDefaultMessage();
        DataResList.add(defaultMessage);
      }
      return DataResList;
    }
   return null;
  }

}
