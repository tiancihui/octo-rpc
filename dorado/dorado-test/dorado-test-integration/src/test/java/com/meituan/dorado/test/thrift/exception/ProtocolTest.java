/*
 * Copyright 2018 Meituan Dianping. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meituan.dorado.test.thrift.exception;

import com.meituan.dorado.test.thrift.apitwitter.Twitter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XMD协议 与 thrift协议 互调
 */
public class ProtocolTest {

    private static final Logger logger = LoggerFactory.getLogger(ProtocolTest.class);

    private static ClassPathXmlApplicationContext clientBeanFactory;
    private static ClassPathXmlApplicationContext serverBeanFactory;
    private static Twitter.Iface client;
    private static Twitter.Iface oldClient;


    @BeforeClass
    public static void start() throws InterruptedException {
        serverBeanFactory = new ClassPathXmlApplicationContext("thrift/exception/oldProtocol/thrift-provider.xml");
        clientBeanFactory = new ClassPathXmlApplicationContext("thrift/exception/oldProtocol/thrift-consumer.xml");
        client = (Twitter.Iface) clientBeanFactory.getBean("clientProxy");
        oldClient = (Twitter.Iface) clientBeanFactory.getBean("oldProtocolClientProxy");
        Thread.sleep(5000);
    }

    @AfterClass
    public static void stop() {
        clientBeanFactory.destroy();
        serverBeanFactory.destroy();
    }

    @Test
    public void testProtocol() {
        try {
            String result = client.testString("message");
            logger.info("Result: {}", result);
        } catch(Exception e) {
            Assert.fail();
        }

        try {
            String result = oldClient.testString("message");
            logger.info("Result: {}", result);
        } catch(Exception e) {
            Assert.fail();
        }
    }
}
