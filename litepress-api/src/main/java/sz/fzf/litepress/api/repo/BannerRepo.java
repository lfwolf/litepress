package sz.fzf.litepress.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sz.fzf.litepress.api.entity.BannerEntity;

public interface BannerRepo extends JpaRepository<BannerEntity, Integer> {

}
