package com.SKTech.spring_boot_json_modifier.Entity;


import com.SKTech.spring_boot_json_modifier.Dto.CurrentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Table(name = "sk_example_table")
@Entity

@Getter
@Setter
public class SkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private CurrentDto obj;

}
