package com.checkaboy.objectutils.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Класс для отправки сообщений на REST API
 *
 * @param <S> класс отправляемый в теле запроса
 * @param <G> класс получаемый в теле ответа
 */
public class RestSender<S, G> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestSender.class);

    public enum LogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        ;
    }

    private static LogLevel logLevel = LogLevel.INFO;

    public static void setLogLevel(LogLevel logLevel) {
        RestSender.logLevel = logLevel;
    }

    private final Class<G> typeParameterClass;
    private final HttpHeaders headers;
    private final RestTemplate restTemplate;
    private S body;
    private String url;

    public RestSender(Class<G> typeParameterClass) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        this.typeParameterClass = typeParameterClass;
    }

    public RestSender(S body, String url, Class<G> typeParameterClass) {
        this(typeParameterClass);
        setBody(body);
        setUrl(url);
    }

    public RestSender setBody(S body) {
        this.body = body;
        return this;
    }

    public RestSender setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RestSender setAuthToken(String token) {
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer_" + token);
        return this;
    }

    public RestSender addHeader(String header, String value) {
        headers.set(header, value);
        return this;
    }

    public RestSender removeHeader(String header) {
        headers.remove(header);
        return this;
    }

    public G sendPost() throws HttpStatusCodeException {
        logging("IN sendPost - запрос по {url=" + url + "}");
        return send(HttpMethod.POST).getBody();
    }

    public G sendGet() throws HttpStatusCodeException {
        logging("IN sendGet - запрос по {url=" + url + "}");
        return send(HttpMethod.GET).getBody();
    }

    public G sendPut() throws HttpStatusCodeException {
        logging("IN sendPut - запрос по {url=" + url + "}");
        return send(HttpMethod.PUT).getBody();
    }

    public G sendDelete() throws HttpStatusCodeException {
        logging("IN sendDelete - запрос по {url=" + url + "}");
        return send(HttpMethod.DELETE).getBody();
    }

    public ResponseEntity<G> send(HttpMethod httpMethod) {
        return restTemplate.exchange(url, httpMethod, buildRequest(), typeParameterClass);
    }

    private void logging(String msg) {
        switch (logLevel) {
            case DEBUG:
                LOGGER.debug(msg);
                break;
            case INFO:
                LOGGER.info(msg);
                break;
            case WARNING:
                LOGGER.warn(msg);
                break;
            case ERROR:
                LOGGER.error(msg);
                break;
        }
    }

    private HttpEntity<S> buildRequest() {
        return new HttpEntity<>(body, headers);
    }

}
