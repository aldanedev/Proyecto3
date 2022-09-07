package pe.com.restapibank.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "comission")
public class Comission {

	@Id
	private Integer idComission;
	private Integer idSaving;
	private Double rate;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idComission == null) ? 0 : idComission.hashCode());
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
        Comission other = (Comission) obj;
        if (idComission == null) {
            if (other.idComission != null)
                return false;
        } else if (!idComission.equals(other.idComission))
            return false;
        return true;
    }
}
