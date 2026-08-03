package Entities.Finance;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallet_transactions")
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String transactionType;
    // Deposit, Withdraw, Refund

    private LocalDateTime transactionDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

}
