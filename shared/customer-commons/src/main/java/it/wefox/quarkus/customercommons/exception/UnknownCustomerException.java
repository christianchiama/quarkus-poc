package it.wefox.quarkus.customercommons.exception;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 01:41
 */
public class UnknownCustomerException extends RuntimeException {
    public final String name;

    public UnknownCustomerException(String name) {
        this.name = name;
    }
}
