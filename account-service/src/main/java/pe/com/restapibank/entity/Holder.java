package pe.com.restapibank.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "holder")
public class Holder {
	
	@Id
	private Integer idHolder;
	private LocalDateTime dateEntry;
	private String userCreation;
	private String ipCreation;
	private Integer idPerson;
	
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idHolder == null) ? 0 : idHolder.hashCode());
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
        Holder other = (Holder) obj;
        if (idHolder == null) {
            if (other.idHolder != null)
                return false;
        } else if (!idHolder.equals(other.idHolder))
            return false;
        return true;
    }

}
