@Entity
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private LocalDateTime createdAt;


    public void setAsset(Asset asset) { this.asset = asset; }
    public Asset getAsset() { return asset; }

    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
