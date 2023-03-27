package org.spburegistry.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicTO {
    private Long clinicId;
    private String link;
    private String name;
    private FacultyTO faculty;
}
