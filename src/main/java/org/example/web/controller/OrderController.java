package org.example.web.controller;


import org.example.common.exception.CommonServiceException;
import org.example.common.vo.BaseResponseVO;
import org.example.entity.Order;
import org.example.service.IOrderService;
import org.example.web.vo.OrderCreateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public BaseResponseVO create(@RequestBody @Validated OrderCreateVO orderCreateVO) throws CommonServiceException {
        orderCreateVO.checkParam();

        Order order = orderService.create(orderCreateVO);
        return BaseResponseVO.success(order);
    }
}
