@Entity
@DiscriminatorValue("INTERNAL")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyInvestor extends Investor {
    private String companyType;

    @Column(unique = true)
    private String lei;
}

@Entity
@DiscriminatorValue("LP")
@Data
@EqualsAndHashCode(callSuper = true)
public class FundLP extends Investor {
    private String lpType;
}

@Entity
@DiscriminatorValue("CO_INVESTOR")
@Data
@EqualsAndHashCode(callSuper = true)
public class CoInvestor extends Investor {
    private String coInvestorType;
}

@Entity
@DiscriminatorValue("LENDER")
@Data
@EqualsAndHashCode(callSuper = true)
public class Lender extends Investor {
    private String lenderType;
}