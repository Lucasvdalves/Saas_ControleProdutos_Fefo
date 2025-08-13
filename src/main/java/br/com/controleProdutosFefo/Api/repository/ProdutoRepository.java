package br.com.controleProdutosFefo.Api.repository;

import br.com.controleProdutosFefo.Api.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <ProdutoModel, Long> {

}
