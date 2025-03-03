package org.springframework.samples.petclinic.domain.vet.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import org.springframework.samples.petclinic.domain.vet.model.enums.VetStatus;
import org.springframework.samples.petclinic.model.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@Entity
@Table(name = "vets")
public class Vet extends BaseEntity {
	@Column(length = 15, nullable = false)
	private String name;

	@Column(precision = 3, scale = 2)
	private BigDecimal averageRatings;

	private Integer reviewCount;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private VetStatus status;

	@PrePersist
	public void prePersist() {
		if (this.status == null) {
			this.status = VetStatus.REGISTERED; // 명시적으로 기본값 적용
		}
	}

	public void updateRatings(BigDecimal newAverageRatings, Integer newReviewCount) {
		this.averageRatings = newAverageRatings;
		this.reviewCount = newReviewCount;
	}
}
