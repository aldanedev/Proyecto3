package pe.com.restapibank.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "account_credit")
public class AccountCredit {

	@Id
	private Integer idCredit;
	private String typeCurrency;
	private String accountNumber;
	private LocalDateTime depositDate;
	private LocalDateTime datePay;
	private Double balance;
	private Double totalConsumed;
	private String countMonth;
	private Double interestPay;
	private Integer idClient;
	private Integer idBank;
	private Integer numberMovMax;
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCredit == null) ? 0 : idCredit.hashCode());
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
        AccountCredit other = (AccountCredit) obj;
        if (idCredit == null) {
            if (other.idCredit != null)
                return false;
        } else if (!idCredit.equals(other.idCredit))
            return false;
        return true;
    }
}
