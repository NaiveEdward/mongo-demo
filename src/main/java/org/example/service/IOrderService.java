package org.example.service;

import org.example.common.exception.CommonServiceException;
import org.example.entity.Order;
import org.example.web.vo.OrderCreateVO;

public interface IOrderService {
    Order create(OrderCreateVO orderCreateVO) throws CommonServiceException;
}
