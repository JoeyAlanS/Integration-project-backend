package com.eletra.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.services.ModelService;
import com.eletra.models.ModelEntity;
import com.eletra.repositories.ModelRepository;
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
    @ApiOperation(value = "Return a model")
    public List<ModelEntity> getModelEntity(@PathVariable(value = "category-name") String categoryName) {
        List<ModelEntity> list = modelService.getModelNameByCategoryName(categoryName);
        return list;
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
    public String deleteModelEntity(@PathVariable(value = "model-name") String modelName) {
        ModelEntity modelEntity = modelRepository.findByModelName(modelName);
        modelRepository.delete(modelEntity);
        return "Model deleted";
    }

    @PutMapping("/model")
    @ResponseBody
    @ApiOperation(value = "Update model")
    public String updateModelEntity(@RequestBody ModelEntity modelEntity) {
        modelRepository.save(modelEntity);
        return "Model updated";
    }
}
