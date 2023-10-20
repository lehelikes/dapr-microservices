package edu.codespring.cloud.usermanagement.service;

public interface DaprPublisherService<T> {

    void publishEvent(T data);
}
