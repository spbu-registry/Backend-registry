package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.CuratorTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.CuratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/data/curators")
public class CuratorController {
    
    @Autowired
    private CuratorService curatorService;

    @GetMapping
    @Operation(description = "Get all curators")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserTO.class)))
    })
    public Iterable<UserTO> getAllCurators() {
        return curatorService.findAll();
    }

    @GetMapping("/curator")
    @Operation(description = "Get curator by id")
    public UserTO getCuratorById(@RequestParam Long id) {
        return curatorService.findById(id);
    }

    @PostMapping("/curator")
    @Operation(description = "Add new curator")
    public CuratorTO addNewcurator(@RequestBody CuratorTO curatorTO) {
        return curatorService.addCurator(curatorTO);
    }
}
