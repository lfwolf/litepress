package sz.fzf.litepress.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sz.fzf.litepress.api.entity.CatalogEntity;

public interface CatalogRepo extends JpaRepository<CatalogEntity, Integer> {

}
