package it.wefox.quarkus.customerservice.mapper;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 11/12/22
 * @Time: 17:35
 */
import com.mongodb.MongoClientSettings;
import it.wefox.quarkus.customerservice.domain.Customer;
import it.wefox.quarkus.petapi.request.PetRequest;
import org.bson.Document;
import org.bson.BsonWriter;
import org.bson.BsonValue;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerCodec implements CollectibleCodec<Customer> {

    private final Codec<Document> documentCodec;

    public CustomerCodec() {
        this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
    }

    @Override
    public void encode(BsonWriter writer, Customer customer, EncoderContext encoderContext) {
        Document doc = new Document();
        doc.put("_id", customer.getId());
        doc.put("name", customer.getName());
        doc.put("address", customer.getAddress());
        doc.put("birthDate", customer.getBirthDate());
        documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<Customer> getEncoderClass() {
        return Customer.class;
    }

    @Override
    public Customer generateIdIfAbsentFromDocument(Customer document) {
        if (!documentHasId(document)) {
            document.setId(new ObjectId());
        }
        return document;
    }

    @Override
    public boolean documentHasId(Customer document) {
        return document.getId() != null;
    }

    @Override
    public BsonValue getDocumentId(Customer document) {
        return new BsonString(document.getId().toString());
    }

    @Override
    public Customer decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        Customer customer = new Customer();
        customer.setId(document.getObjectId("_id"));
        customer.setName(document.getString("name"));
        customer.setAddress(document.getString("address"));
        customer.setBirthDate(convertToLocalDate((Date)document.get("birthDate")));
        customer.setPets((List<? extends PetRequest>) document.get("pets"));
        return customer;
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
