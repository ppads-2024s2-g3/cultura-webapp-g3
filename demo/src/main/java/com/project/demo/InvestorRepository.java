@Repository
public interface InvestorRepository
    extends JpaRepository<Investor, Long>, JpaSpecificationExecutor<Investor> {}

@UtilityClass
public class InvestorSpecifications {
  public static Specification<Investor> hasType(Class<? extends Investor> type) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.type(), type);
  }
}