package br.com.controleProdutosFefo.Api.service;

import br.com.controleProdutosFefo.Api.dto.ProdutoRequestDTO;
import br.com.controleProdutosFefo.Api.dto.ProdutoResponseDTO;
import br.com.controleProdutosFefo.Api.dto.UserRequestDTO;
import br.com.controleProdutosFefo.Api.dto.UserResponseDTO;
import br.com.controleProdutosFefo.Api.model.ProdutoModel;
import br.com.controleProdutosFefo.Api.model.UserModel;
import br.com.controleProdutosFefo.Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> buscarProdutosProximoVencimentos() {
      return  produtoRepository.findAll().stream()
              .sorted(Comparator.comparing(ProdutoModel::getDataValidade))
              .map(this::toDTO)
                .toList();
    }

    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        ProdutoModel produto = toEntity(dto);
        ProdutoModel savedProduto = produtoRepository.save(produto);
        return toDTO(savedProduto);
    }

    public Optional<ProdutoResponseDTO> atualizarProduto(Long id, ProdutoRequestDTO dto) {
      return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNomeProduto(dto.getNomeProduto());
                    produtoExistente.setLote(dto.getLote());
                    produtoExistente.setQuantidade(dto.getQuantidade());
                    produtoExistente.setDataValidade(dto.getDataValidade());
                    produtoExistente.setDataCompra(dto.getDataCompra());
                    ProdutoModel updatedProduto = produtoRepository.save(produtoExistente);
                    return toDTO(updatedProduto);
                });
    }

    public boolean deletarProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProdutoModel toEntity(ProdutoRequestDTO dto) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNomeProduto(dto.getNomeProduto());
        produto.setLote(dto.getLote());
        produto.setQuantidade(dto.getQuantidade());
        produto.setDataValidade(dto.getDataValidade());
        produto.setDataCompra(dto.getDataCompra());
        return produto;
    }

    private ProdutoResponseDTO toDTO(ProdutoModel produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getLote(),
                produto.getQuantidade(),
                produto.getDataValidade(),
                produto.getDataCompra()
        );
    }

}
