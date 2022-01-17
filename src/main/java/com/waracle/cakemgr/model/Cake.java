package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cake {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String desc;
    private String image;
}
