import javax.persistence.*;

@Entity
@DiscriminatorValue("CompanyInvestor")
public class CompanyInvestor extends Investor {
    private String companyType;

    // Getters e Setters
}
