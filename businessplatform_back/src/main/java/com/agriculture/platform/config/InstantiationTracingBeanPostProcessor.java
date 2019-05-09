package com.agriculture.platform.config;

import com.agriculture.platform.auctionMonitor.MonitorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Timer;

/**
 * @author Junbo Wang
 * @description spring容器初始化完成后业务操作
 * @date 2019/5/9
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("spring容器初始化完成");
        }

        // 判断根容器为Spring容器，防止出现调用两次的情况（mvc加载也会触发一次）
        if(event.getApplicationContext().getParent() == null) {
            long interval = 1000 * 1000; //默认10秒
            //开启线程，注册竞价物品竞价期限检测服务
            LOGGER.debug("注册竞拍商品定时检测任务，间隔时间为" + interval);
            MonitorTask monitorTask = new MonitorTask();
            Timer timer = new Timer();
            timer.schedule(monitorTask, 0, interval);
        }
    }
}
