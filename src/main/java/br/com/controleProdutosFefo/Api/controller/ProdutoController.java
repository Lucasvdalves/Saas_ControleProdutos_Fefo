package br.com.controleProdutosFefo.Api.controller;

import br.com.controleProdutosFefo.Api.dto.ProdutoRequestDTO;
import br.com.controleProdutosFefo.Api.dto.ProdutoResponseDTO;
import br.com.controleProdutosFefo.Api.model.ProdutoModel;
import br.com.controleProdutosFefo.Api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    final private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutosProximoVencimentos();
        if(produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody ProdutoRequestDTO produto, UriComponentsBuilder uriBuilder) {
        ProdutoResponseDTO novoProduto = produtoService.cadastrarProduto(produto);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(novoProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDTO produtoDto) {

        Optional<ProdutoResponseDTO> produtoAtualizado = produtoService.atualizarProduto(id, produtoDto);

        if (produtoAtualizado.isPresent()) {
            return ResponseEntity.ok(produtoAtualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        boolean isDeleted = produtoService.deletarProduto(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
