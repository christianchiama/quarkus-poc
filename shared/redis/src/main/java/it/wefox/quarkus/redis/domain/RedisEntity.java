package it.wefox.quarkus.redis.domain;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 10:16
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RedisEntity<K,V> {

    public K key;
    public V value;

}

