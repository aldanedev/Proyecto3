package pe.com.restapibank.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee {

	@Id
	private Integer idEmployee;
	private LocalDateTime dateEntry;
	private String userCreation;
	private String ipCreation;
	private Integer idPerson;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmployee == null) ? 0 : idEmployee.hashCode());
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
        Employee other = (Employee) obj;
        if (idEmployee == null) {
            if (other.idEmployee != null)
                return false;
        } else if (!idEmployee.equals(other.idEmployee))
            return false;
        return true;
    }
}
