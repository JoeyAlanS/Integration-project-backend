package com.eletra.controllers;

import com.eletra.models.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.services.ModelService;
import com.eletra.models.ModelEntity;
import com.eletra.repositories.ModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Medidores")
@CrossOrigin(origins = "*")
public class ModelController {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @GetMapping("/models")
    @ResponseBody
    @ApiOperation(value = "Return models")
    public List<ModelEntity> getModelEntityList() {
        return modelRepository.findAll();
    }

    @GetMapping("/models/{category-name}")
    @ResponseBody
    @ApiOperation(value = "Return models by category")
    public List<ModelEntity> getModelEntityByCategory(@PathVariable(value = "category-name") String categoryName) {
        return modelService.getModelNameByCategoryName(categoryName);
    }

    @PostMapping("/model")
    @ResponseBody
    @ApiOperation(value = "Add Model")
    public ModelEntity postModelEntity(@RequestBody ModelEntity modelEntity) {
        return modelRepository.save(modelEntity);
    }

    @DeleteMapping("/model/{model-name}")
    @ResponseBody
    @ApiOperation(value = "Delete model")
    public ResponseEntity<Boolean> deleteModelEntity(@PathVariable(value = "model-name") String modelName) {
        ModelEntity modelEntity = modelRepository.findByModelName(modelName);
        if (modelEntity != null) {
            modelRepository.delete(modelEntity);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PutMapping("/model")
    @ResponseBody
    @ApiOperation(value = "Update model")
    public ModelEntity updateModelEntity(@RequestBody ModelEntity modelEntity) {
        return modelRepository.save(modelEntity);
    }
}
