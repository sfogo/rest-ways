package com.vnet.camelcxf;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MappingProcessor implements Processor {
    final private Log logger = LogFactory.getLog(getClass());
    final private Class<?> beanClass;
    final private Object instance;

    MappingProcessor(Object obj) {
        this.beanClass = obj.getClass();
        this.instance = obj;
    }

    public void process(Exchange exchange) throws Exception {
        final String methodName = exchange.getIn().getHeader(CxfConstants.OPERATION_NAME, String.class);
        final Object[] parameters = exchange.getIn().getBody(Object[].class);
        final Method method = getMethod(methodName, parameters);
        try {
            Object response = method.invoke(instance, parameters);
            exchange.getOut().setBody(response);
        }  catch (InvocationTargetException e) {
            logger.error(e.getCause().getMessage());
            throw (Exception) e.getCause();
        }
    }

    private Method getMethod(String methodName, Object[] parameters) throws NoSuchMethodException {
        logger.info(String.format("Find Method : %s.%s", beanClass.getName(), methodName));
        if (parameters == null)
            return beanClass.getMethod(methodName);

        final Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterTypes[i] = parameters[i].getClass();
            logger.info(String.format("With Parameter: %s [%s]",parameterTypes[i].getName(), parameters[i].toString()));
        }
        return beanClass.getMethod(methodName, parameterTypes);
    }
}