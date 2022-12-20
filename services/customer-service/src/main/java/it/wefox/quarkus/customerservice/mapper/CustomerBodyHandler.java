package it.wefox.quarkus.customerservice.mapper;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 01:49
 */

import com.google.gson.Gson;
import it.wefox.quarkus.customercommons.Response;
import it.wefox.quarkus.customerservice.domain.Customer;
import org.jboss.resteasy.reactive.server.spi.ResteasyReactiveResourceInfo;
import org.jboss.resteasy.reactive.server.spi.ServerMessageBodyWriter;
import org.jboss.resteasy.reactive.server.spi.ServerRequestContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerBodyHandler<K> implements MessageBodyReader<K>,
        ServerMessageBodyWriter<K> {

    Class<K> entity;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,ResteasyReactiveResourceInfo target, MediaType mediaType) {
        return type == Customer.class;
    }

    /**
     * @param aClass
     * @param type
     * @param annotations
     * @param mediaType
     * @return
     */
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == entity;
    }

    /**
     *
     * @param t
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @param httpHeaders
     * @param entityStream
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public void writeTo(K t, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
            throws IOException, WebApplicationException {
        entityStream.write(("[CustomerV1]")
                .getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public void writeResponse(K t, Type genericType, ServerRequestContext context)
            throws WebApplicationException {
        Gson gson = new Gson();
        Response<K> response = Response.of(t,200, "Ok");
        String json = gson.toJson(response);
        context.serverResponse().end(json);
    }
    /**
     *
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @return
     */
    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        return type == entity.getClass();
    }

    /**
     *
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @param httpHeaders
     * @param entityStream
     * @return
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public K readFrom(Class<K> type, Type genericType,
                             Annotation[] annotations, MediaType mediaType,
                             MultivaluedMap<String, String> httpHeaders,
                             InputStream entityStream)
            throws IOException, WebApplicationException {
        String body = new String(entityStream.readAllBytes(), StandardCharsets.UTF_8);
        K clazz = instantiateNew(entity);
        return clazz;
    }

    @SuppressWarnings("unchecked")
    K instantiateNew(Class<?> clsT)
    {
        K newT;
        try {
            newT = (K) clsT.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return newT;
    }

}