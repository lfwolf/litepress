package sz.fzf.litepress.api.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sz.fzf.litepress.api.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	public Page<PostEntity> findByKindAndNameIsNotNull(Integer kind, Pageable pageable);
}
