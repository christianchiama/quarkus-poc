package it.wefox.quarkus.customercommons;

import lombok.*;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 02:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Response<K> {

    private K data;
    private int status;
    private String message;

    public static <K> Response<K> of(K data, int status, String message){
        return new Response<>(data,status,message);
    }
}
