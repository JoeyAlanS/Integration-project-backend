package com.eletra.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
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
    public String deleteLineupEntity(@PathVariable(value = "line-name") String lineName) {
        LineupEntity lineupEntity = lineupRepository.findByLineName(lineName);
        lineupRepository.delete(lineupEntity);
        return "Line deleted";
    }

    @PutMapping("/line")
    @ResponseBody
    @ApiOperation(value = "Update line")
    public String updateLineEntity(@RequestBody LineupEntity lineupEntity) {
        lineupRepository.save(lineupEntity);
        return "Line updated";
    }
}
