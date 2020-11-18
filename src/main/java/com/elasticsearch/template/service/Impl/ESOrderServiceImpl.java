package com.elasticsearch.template.service.Impl;

import com.elasticsearch.template.component.ESOrderConverter;
import com.elasticsearch.template.dto.OrderDTO;
import com.elasticsearch.template.entity.ESOrder;
import com.elasticsearch.template.repository.ESOrderRepository;
import com.elasticsearch.template.service.OrderService;
import com.elasticsearch.template.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESOrderServiceImpl implements OrderService {

    @Autowired
    ESOrderRepository repository;

    public OrderDTO addOrder(ESOrder ESOrder){
        ESOrder.setOrderTime(DateUtil.DateFormatter());
        ESOrder saveESOrder = repository.save(ESOrder);
        return ESOrderConverter.toDTO(saveESOrder);
    }

    @Override
    public List<OrderDTO> allOrder(){
        List<ESOrder> ESOrder = repository.findAll();
        return ESOrder.stream().map(ESOrderConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findOrder(int OrderId){
        List<ESOrder> ESOrder = repository.findByorderId(OrderId);
        return ESOrder.stream().map(ESOrderConverter:: toDTO).collect(Collectors.toList());
    }

    public List<OrderDTO> findByorderTenderType(int orderTenderType){
        List<ESOrder> ESOrder = repository.findByorderTenderType(orderTenderType);
        return ESOrder.stream().map(ESOrderConverter:: toDTO).collect(Collectors.toList());
    }
}