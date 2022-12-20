package it.wefox.quarkus.treatmentservice.mapper;

import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import it.wefox.quarkus.treatmentservice.domain.Treatment;

import javax.inject.Singleton;

/**
 * @author Christian Chiama
 * --
 */
@Singleton
public class TreatmentMapper {

    public  java.util.function.Function<Treatment, TreatmentRequest> mapTreatmentEntityToRequest() {
        return treatment -> new TreatmentRequest(
                treatment.getId(),
                treatment.getName(),
                treatment.getPetId(),
                treatment.getCreatedAt(),
                treatment.getPrice(),
                treatment.getResult());
    }

    public  java.util.function.Function<TreatmentRequest, Treatment> mapTreatmentRequestToEntity() {
        return treatmentRequest -> new Treatment(
                treatmentRequest.getId(),
                treatmentRequest.getName(),
                treatmentRequest.getPetId(),
                treatmentRequest.getCreatedAt(),
                treatmentRequest.getPrice(),
                treatmentRequest.getResult()
        );
    }
}
