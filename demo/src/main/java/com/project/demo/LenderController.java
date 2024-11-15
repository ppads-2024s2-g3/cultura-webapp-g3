package com.project.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lenders")
public class LenderController {

    @Autowired
    private InvestorRepository InvestorRepository;

    // Retorna todos os Lenders
    @GetMapping
    public List<Lender> getLenders() {
        return InvestorRepository.findAll()
                .stream()
                .filter(investor -> investor instanceof Lender)
                .map(investor -> (Lender) investor)
                .collect(Collectors.toList());
    }

    // Retorna um Lender específico pelo ID
    @GetMapping("/{id}")
    public Optional<Lender> getLender(@PathVariable long id) {
        return InvestorRepository.findById(id)
                .filter(investor -> investor instanceof Lender)
                .map(investor -> (Lender) investor);
    }

    // Cria um novo Lender
    @PostMapping
    public Lender createLender(@RequestBody Lender lender) {
        return InvestorRepository.save(lender);
    }

    // Atualiza um Lender específico pelo ID
    @PutMapping("/{lenderId}")
    public Optional<Lender> updateLender(@RequestBody Lender lenderRequest,
                                          @PathVariable long lenderId) {
        Optional<Lender> opt = InvestorRepository.findById(lenderId)
                .filter(investor -> investor instanceof Lender)
                .map(investor -> (Lender) investor);

        if (opt.isPresent()) {
            if (lenderRequest.getId().equals(lenderId)) {
                InvestorRepository.save(lenderRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do lender com id " + lenderId);
    }

    // Deleta um Lender específico pelo ID
    @DeleteMapping("/{id}")
    public void deleteLender(@PathVariable long id) {
        InvestorRepository.deleteById(id);
    }
}
