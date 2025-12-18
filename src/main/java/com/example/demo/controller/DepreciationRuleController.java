@Entity
public class DepreciationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String method;
    private Integer usefulLifeYears;
    private Double salvageValue;
    private LocalDateTime createdAt;

    public Integer getUsefulLifeYears() { return usefulLifeYears; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
