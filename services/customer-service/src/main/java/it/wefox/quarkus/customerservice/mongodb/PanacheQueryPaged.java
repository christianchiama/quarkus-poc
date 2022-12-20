package it.wefox.quarkus.customerservice.mongodb;


import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import it.wefox.quarkus.customerservice.domain.Customer;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 19/12/22
 * @Time: 23:38
 */
public class PanacheQueryPaged {


    private final Logger LOGGER= Logger.getLogger(PanacheQueryPaged.class);


    public void createQueryPaged(){
        PanacheQuery<Customer> livingPersons = (PanacheQuery<Customer>) Customer.findAll();
        livingPersons.page(Page.ofSize(25));
        List<Customer> firstPage = livingPersons.list();
        List<Customer> secondPage = livingPersons.nextPage().list();
        List<Customer> page7 = livingPersons.page(Page.of(7, 25)).list();
        int numberOfPages = livingPersons.pageCount();
    }
}
