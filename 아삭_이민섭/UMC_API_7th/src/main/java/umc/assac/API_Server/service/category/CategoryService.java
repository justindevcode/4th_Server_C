package umc.assac.API_Server.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.assac.API_Server.domain.category.Category;
import umc.assac.API_Server.dto.category.CategoryCreateDto;
import umc.assac.API_Server.exception.category.CategoryNotFoundException;
import umc.assac.API_Server.repository.category.CategoryRepository;

// 카테고리의 생성과 삭제 로직을 담당
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 저장 로직
    @Transactional
    public void saveCategory(CategoryCreateDto createDto) {
        Category category = new Category(createDto.getCategoryName());
        categoryRepository.save(category);
    }

    // 카테고리 삭제 로직
    @Transactional
    public void deleteCategory(Long categoryId) {
        if(!categoryRepository.existsById(categoryId)) throw new CategoryNotFoundException();
        categoryRepository.deleteById(categoryId);
    }
}
