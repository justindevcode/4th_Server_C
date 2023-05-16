package umc.assac.API_Server.controller.category;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.assac.API_Server.dto.category.CategoryCreateDto;
import umc.assac.API_Server.service.category.CategoryService;

import javax.validation.Valid;

// 카테고리의 생성과 삭제 로직을 담당하는 Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리를 새롭게 생성하는 로직
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "카테고리 생성", notes = "신규 카테고리를 새롭게 생성하는 로직")
    @ApiImplicitParam(name = "createDto", value = "신규 카테고리를 생성하기 위한 정보가 담겨있는 DTO")
    public void saveCategory(@RequestBody @Valid CategoryCreateDto createDto) {
        categoryService.saveCategory(createDto);
    }

    // 특정 카테고리를 삭제하는 로직
    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "카테고리 삭제", notes = "특정 카테고리를 삭제하는 로직")
    @ApiImplicitParam(name = "id", value = "삭제할 카테고리를 식별하기 위한 PK값")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
