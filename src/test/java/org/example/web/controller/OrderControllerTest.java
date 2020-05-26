package org.example.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.example.common.RestUtils;
import org.example.common.vo.BaseResponseVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

@Slf4j
public class OrderControllerTest {
    @Test
    public void testCreate() {
        String url = "http://localhost:9000/order/create";

        RestTemplate restTemplate = RestUtils.getRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestData = objectMapper.createObjectNode();

        String userId = "5ec37f776639c44199bff825";
        String itemId = "5ec3f70c41c52754d67f6b47";
        Integer count = 2;

        requestData.put("userId", userId);
        requestData.put("itemId", itemId);
        requestData.put("count", count);

        HttpEntity<String> request = new HttpEntity<>(requestData.toString(), headers);
        ResponseEntity<BaseResponseVO> responseEntity = restTemplate.postForEntity(url, request, BaseResponseVO.class);

        log.debug(responseEntity.toString());

        // <1> statusCode
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
        BaseResponseVO response = responseEntity.getBody();

        log.debug(response.toString());
        // <2> code ： 200 成功， 3003 库存不足
        boolean flag = response.getCode().equals(200) || response.getCode().equals(3003);
        Assert.assertTrue(flag);

        if (response.getCode().equals(200)) {
            LinkedHashMap data = (LinkedHashMap) response.getData();
            // <3> 数据判断
            Assert.assertTrue(data.get("userId").equals(userId));
            Assert.assertTrue(data.get("itemId").equals(itemId));
            Assert.assertTrue(data.get("count").equals(count));
        }
    }

}