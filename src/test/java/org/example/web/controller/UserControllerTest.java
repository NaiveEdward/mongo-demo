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
public class UserControllerTest {

    @Test
    public void testRegister() {
        String url = "http://localhost:9000/user/register";
        String account = "18707159999";
        String password = "123456";

        RestTemplate restTemplate = RestUtils.getRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestData = objectMapper.createObjectNode();
        requestData.put("account", account);
        requestData.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(requestData.toString(), headers);
        ResponseEntity<BaseResponseVO> responseEntity = restTemplate.postForEntity(url, request, BaseResponseVO.class);

        log.debug(responseEntity.toString());

        // <1> statusCode
        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
        BaseResponseVO response = responseEntity.getBody();

        log.debug(response.toString());
        // <2> code ： 200 成功， 2001 已存在
        boolean flag = response.getCode().equals(200) || response.getCode().equals(2001);
        Assert.assertTrue(flag);

        if (response.getCode().equals(200)) {
            LinkedHashMap data = (LinkedHashMap) response.getData();
            // <3> account判断
            Assert.assertTrue(data.get("account").equals(account));
        }

        // <4> 控制判断
        requestData = objectMapper.createObjectNode();
        requestData.put("account", "");
        requestData.put("password", "");

        request = new HttpEntity<>(requestData.toString(), headers);
        responseEntity = restTemplate.postForEntity(url, request, BaseResponseVO.class);

        log.debug(responseEntity.toString());

        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
        response = responseEntity.getBody();
        // statusCode
        Assert.assertEquals(response.getCode(), Integer.valueOf(400));

        // <5> 输入不合法判断
        requestData = objectMapper.createObjectNode();
        requestData.put("account", "12423");
        requestData.put("password", "111");

        request = new HttpEntity<>(requestData.toString(), headers);
        responseEntity = restTemplate.postForEntity(url, request, BaseResponseVO.class);

        log.debug(responseEntity.toString());

        Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
        response = responseEntity.getBody();
        // statusCode
        Assert.assertEquals(response.getCode(), Integer.valueOf(400));
    }

    @Test
    public void testLogin() {
    }

    @Test
    public void testSaveInfo() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testSaveTag() {
    }

    @Test
    public void testSaveContact() {
    }

    @Test
    public void testRemoveContact() {
    }


}