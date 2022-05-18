package com.buba.springcloud.controller;

import com.buba.springcloud.pojo.CommonResult;
import com.buba.springcloud.pojo.Payment;
import com.buba.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    /**
     * paymentservice02
     */
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if(!logger.isInfoEnabled()){
            logger.info("=======插入成功=============="+i);
        }
        //判断是否插入成功
        if(i>0){
            return new CommonResult(200,"插入db成功",i);
        }else {
            return new CommonResult(444,"插入db失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){

        Payment payment = paymentService.queryById(id);
        if(!logger.isInfoEnabled()){
            logger.info("=======查询成功==========="+payment);
        }

        //0518注释01
        if(payment==null){
            return new CommonResult(444,"查询失败",null);
        }else {
            return new CommonResult(200,"查询成功"+port,payment);
        }
    }
}
