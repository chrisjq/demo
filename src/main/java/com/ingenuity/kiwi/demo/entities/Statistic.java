package com.ingenuity.kiwi.demo.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ingenuity.kiwi.demo.entities.enums.StatsDayEnum;
import com.ingenuity.kiwi.demo.entities.enums.StatsTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "statistic")
public class Statistic {
	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_stat_department"))
	private Department department;

	@NonNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_stat_activity"))
	private Activity activity;

	private StatsTypeEnum type;

	private StatsDayEnum dayEnum;

	private Long timeSpendSeconds;
}