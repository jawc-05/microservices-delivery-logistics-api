/**
 * @author jawc
 */
package br.com.jawc.logistics.delivery_service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_courier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourierStatus status;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = CourierStatus.OFFLINE;
        }
    }

}
