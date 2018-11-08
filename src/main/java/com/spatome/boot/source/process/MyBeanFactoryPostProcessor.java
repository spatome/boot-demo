package com.spatome.boot.source.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import com.spatome.boot.source.bean.SimpleBeanInBeanFactoryPostProcessor;
import com.spatome.boot.source.bean.SimpleBeanWithDefinitionInBeanFactoryPostProcessor;

/**
 * Created by Format on 2017/5/9.
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("------ MyBeanFactoryPostProcessor construct");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("------ register custom bean in BeanFactoryPostProcessor");
        beanFactory.registerSingleton("createByBeanFactoryPostProcessor", new SimpleBeanInBeanFactoryPostProcessor());
        if(beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionReaderUtils.registerBeanDefinition(
                    new BeanDefinitionHolder(new AnnotatedGenericBeanDefinition(SimpleBeanWithDefinitionInBeanFactoryPostProcessor.class), "simpleBeanWithDefinitionInBeanFactoryPostProcessor"),                             (BeanDefinitionRegistry) beanFactory
            );
        }
    }

}
