package pe.com.restapibank.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "client")
public class Client {

	@Id
	private Integer idClient;
	private String typeClient;
	private LocalDateTime dateEntry;
	private String userCreation;
	private String ipCreation;
	private Integer idPerson;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idClient == null) ? 0 : idClient.hashCode());
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
        Client other = (Client) obj;
        if (idClient == null) {
            if (other.idClient != null)
                return false;
        } else if (!idClient.equals(other.idClient))
            return false;
        return true;
    }
}
