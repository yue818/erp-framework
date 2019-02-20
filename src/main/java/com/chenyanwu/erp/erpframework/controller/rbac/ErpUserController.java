package com.chenyanwu.erp.erpframework.controller.rbac;

import com.chenyanwu.erp.erpframework.common.PageResultBean;
import com.chenyanwu.erp.erpframework.common.ResultBean;
import com.chenyanwu.erp.erpframework.exception.ExceptionEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.chenyanwu.erp.erpframework.service.rbac.ErpUserService;
import com.chenyanwu.erp.erpframework.entity.rbac.ErpUser;

import java.util.List;

/**
* <p>
    * </p>
*
* @author chenyanwu
* @date 2019-02-20 11:17:03
* @version
*/
@Controller

@RequestMapping(value = "/erpuser")
public class ErpUserController {

Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
private ErpUserService service;



@RequestMapping(value = "/get",method = RequestMethod.GET)
@ResponseBody
public ResultBean<ErpUser> get(String id){
ErpUser item= (ErpUser) service.selectByPrimaryKey(id);
if(item!=null){
return new ResultBean<>(item);
}
else {
return new ResultBean<>(ExceptionEnum.RESOURCE_NOT_FOUND,null,"找不到该记录",null);
}
}


@RequestMapping(value = "/getlist",method = RequestMethod.GET)
@ResponseBody
public PageResultBean<List<ErpUser>> getList(int page,int limit){
List<ErpUser> list;
PageHelper.startPage(page, limit);
list = service.selectAll();
return new PageResultBean(list,page,limit, ((Page) list).getTotal());

}

@RequestMapping(value = "/create",method = RequestMethod.POST)
@ResponseBody
public ResultBean<String> create(@RequestBody @Validated ErpUser item){
    service.insertSelective(item);
    return new ResultBean<String>("");
}

@RequestMapping(value = "/update",method = RequestMethod.POST)
@ResponseBody
public ResultBean<String> update(@RequestBody @Validated ErpUser item){
            service.updateByPrimaryKey(item);
    return new ResultBean<String>("");
}

@RequestMapping(value = "/deleteByID",method = RequestMethod.POST)
@ResponseBody
public ResultBean<Integer> delete(String id){
    int result= service.deleteByPrimaryKey(id);
    return new ResultBean<Integer>(result);
}

@RequestMapping(value = "/delete",method = RequestMethod.POST)
@ResponseBody
public ResultBean<Integer> delete( @RequestBody ErpUser item){
    int result= service.delete(item);
    return new ResultBean<Integer>(result);
}

@GetMapping("index")
public ModelAndView index(){
return new ModelAndView( );
}

@GetMapping("add")
public ModelAndView add(){
return new ModelAndView();
}

@GetMapping("edit")
public ModelAndView edit(String id){
ModelAndView modelAndView = new ModelAndView();
ErpUser item=(ErpUser)service.selectByPrimaryKey(id);
modelAndView.addObject("entity",item );
return modelAndView;
}


}