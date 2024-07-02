package com.eletra.controllers;

import com.eletra.models.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Medidores")
@CrossOrigin(origins = "*")
public class LineupController {
    @Autowired
    private LineupRepository lineupRepository;

    @GetMapping("/lines")
    @ResponseBody
    @ApiOperation(value = "Return lines")
    public List<LineupEntity> getLineupEntityList() {
        return lineupRepository.findAll();
    }

    @GetMapping("/lines/{line-name}")
    @ResponseBody
    @ApiOperation(value = "Return line")
    public LineupEntity getLineupEntity(@PathVariable(value = "line-name") String lineName) {
        return lineupRepository.findByLineName(lineName);
    }

    @PostMapping("/line")
    @ResponseBody
    @ApiOperation(value = "Add Member to Lineup")
    public LineupEntity postLineupEntity(@RequestBody LineupEntity lineupEntity) {
        return lineupRepository.save(lineupEntity);
    }

    @DeleteMapping("/line/{line-name}")
    @ResponseBody
    @ApiOperation(value = "Delete line")
    public ResponseEntity<Boolean> deleteLineupEntity(@PathVariable(value = "line-name") String lineName) {
        LineupEntity lineupEntity = lineupRepository.findByLineName(lineName);
        if (lineupEntity != null) {
            lineupRepository.delete(lineupEntity);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/line")
    @ResponseBody
    @ApiOperation(value = "Update line")
    public LineupEntity updateLineupyEntity(@RequestBody LineupEntity lineupEntity) {
        return lineupRepository.save(lineupEntity);
    }
}
