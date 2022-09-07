package pe.com.restapibank.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Creación de objeto con MongoDB
 * Uso de colección
 */

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movement")
public class Movement {

    @Id
    private Integer idMovementFixed;
    private Integer idCredit;
    private Integer idFixed;
    private Integer idSaving;
    private LocalDateTime dateMovement;    
    private String type;
    private String description;
    private Double interest;    
    private Double balance;

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idMovementFixed == null) ? 0 : idMovementFixed.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movement other = (Movement) obj;
        if (idMovementFixed == null) {
            if (other.idMovementFixed != null)
                return false;
        } else if (!idMovementFixed.equals(other.idMovementFixed))
            return false;
        return true;
    }
}
