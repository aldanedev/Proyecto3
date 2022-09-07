package pe.com.restapibank.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "account_saving")
public class AccountSaving {

	@Id
	private Integer idSaving;
	private String typeSaving;
	private String typeCurrency;
	private String typeAccount;
	private String accountNumber;
	private LocalDateTime depositDate;
	private Double balance;
	private Double interest;
	private Integer idClient;
	private Integer numberMovMonth;
	private Integer numberMovMax;
	private Integer interbankAccount;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSaving == null) ? 0 : idSaving.hashCode());
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
        AccountSaving other = (AccountSaving) obj;
        if (idSaving == null) {
            if (other.idSaving != null)
                return false;
        } else if (!idSaving.equals(other.idSaving))
            return false;
        return true;
    }
	
}
